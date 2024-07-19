package com.samganira.food_order_ms.service;

import com.samganira.food_order_ms.mapper.KafkaStatus;
import com.samganira.food_order_ms.mapper.Menu;
import com.samganira.food_order_ms.mapper.entity.Payment;
import com.samganira.food_order_ms.mapper.dto.OrderDTO;
import com.samganira.food_order_ms.mapper.response.OrderResponse;
import com.samganira.food_order_ms.repository.FoodRepository;
import com.samganira.food_order_ms.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final PaymentRepository paymentRepository;
    private final KafkaProducerService kafkaProducerService;

    public Menu showMenu() {
        return foodRepository.showMenu();
    }

    public OrderResponse orderFood(OrderDTO orderDTO) {
        double bill = 0.0;
        Menu menu = foodRepository.showMenu();
        for (String name : orderDTO.getFoodNames()) {
            Double priceForItem = getPriceForItem(name, menu);
            if (priceForItem != null) {
                bill = bill + priceForItem;
            }
        }
        Payment payment = orderDTO.getPayment();
        boolean existsByCardNumber = paymentRepository.existsByCardNumber(payment.getCardNumber());
        Double balance = paymentRepository.retrieveBalanceByCard(payment.getCardNumber());
        if (!existsByCardNumber)
            return new OrderResponse("Your card number is incorrect"); // todo throw exception in this case
        else if (bill > balance) {
            return new OrderResponse("You do not have enough amount in your bank account.");
        } else if (bill <= balance) {
            balance = balance - bill;
            payment.setBalance(balance);
            paymentRepository.updateBalanceByCardNumber(payment.getCardNumber(), payment.getBalance());
            kafkaProducerService.sendFoodNames(orderDTO);
            return new OrderResponse("Your order is in progress.");
        }
        return null;
    }

    public boolean nonExistMenu() {
        return foodRepository.count() == 0;
    }

    public Menu save(Menu menu) {
        return foodRepository.save(menu);
    }

    private Double getPriceForItem(String itemName, Menu menu) {
        for (Menu.Item burger : menu.getBurgers()) {
            if (burger.getName().equalsIgnoreCase(itemName)) {
                return burger.getPrice();
            }
        }
        for (Menu.Item salad : menu.getSalads()) {
            if (salad.getName().equalsIgnoreCase(itemName)) {
                return salad.getPrice();
            }
        }
        for (Menu.Item drink : menu.getDrinks()) {
            if (drink.getName().equalsIgnoreCase(itemName)) {
                return drink.getPrice();
            }
        }
        return null;
    }
}