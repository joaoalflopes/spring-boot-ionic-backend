package com.jboyCorp.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jboyCorp.course.entities.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
