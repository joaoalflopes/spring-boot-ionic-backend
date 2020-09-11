package com.jboyCorp.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jboyCorp.course.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
