package com.jboyCorp.course.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jboyCorp.course.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	
	@Transactional(readOnly = true)
	@Query("SELECT obj from City obj WHERE obj.state.id = :stateId ORDER BY obj.name")
	public List<City> findCities(@Param("stateId") Long State_id);

}
