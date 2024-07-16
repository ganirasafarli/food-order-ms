package com.samganira.food_order_ms.repository;

import com.samganira.food_order_ms.mapper.entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment save(Payment payment);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Payment p WHERE p.cardNumber = :cardNumber")
    boolean existsByCardNumber(String cardNumber);

    @Modifying
    @Transactional
    @Query("UPDATE Payment p SET p.balance = :balance WHERE p.cardNumber = :cardNumber")
    void updateBalanceByCardNumber(@Param("cardNumber") String cardNumber, @Param("balance") Double balance);

    @Query("select p.balance from Payment p where p.cardNumber=:cardNumber")
    Double retrieveBalanceByCard(String cardNumber);
}
