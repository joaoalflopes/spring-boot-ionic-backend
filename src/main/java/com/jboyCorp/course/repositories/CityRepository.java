package com.jboyCorp.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jboyCorp.course.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
