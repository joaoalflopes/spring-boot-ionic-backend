package com.jboyCorp.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jboyCorp.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
