package com.newkumar.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newkumar.springboot.model.Skv;

@Repository
public interface SkvRepository extends JpaRepository<Skv, Long> {

    Skv findByName(String name);

}
		