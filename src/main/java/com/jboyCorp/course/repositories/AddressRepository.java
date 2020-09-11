package com.jboyCorp.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jboyCorp.course.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
