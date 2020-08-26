package com.jboyCorp.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jboyCorp.course.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
