package com.microservice.ms2.microservice2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Model, Integer> {
}
