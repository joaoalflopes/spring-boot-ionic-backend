package com.jboyCorp.course.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jboyCorp.course.entities.Order;
import com.jboyCorp.course.entities.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Transactional(readOnly = true)
	Page<Order> findByUser(User user, Pageable pageRequest);

}
