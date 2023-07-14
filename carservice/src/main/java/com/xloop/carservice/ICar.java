package com.xloop.carservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICar extends JpaRepository<Car, Long> {
    
}
