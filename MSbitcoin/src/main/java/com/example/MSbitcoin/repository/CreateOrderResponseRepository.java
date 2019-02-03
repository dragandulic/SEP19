package com.example.MSbitcoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MSbitcoin.dto.CreateOrderResponse;

@Repository
public interface CreateOrderResponseRepository extends JpaRepository<CreateOrderResponse, Long>{

	CreateOrderResponse findByIdourEquals(Long id);
	
}
