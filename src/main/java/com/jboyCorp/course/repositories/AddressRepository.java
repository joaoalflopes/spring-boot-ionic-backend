package com.jboyCorp.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jboyCorp.course.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
