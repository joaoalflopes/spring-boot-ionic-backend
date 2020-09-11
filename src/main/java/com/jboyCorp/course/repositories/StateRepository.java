package com.jboyCorp.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jboyCorp.course.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
