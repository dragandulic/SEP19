package com.example.KPzuulproxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.KPzuulproxy.model.ObjectPayment;

@Repository
public interface ObjectPaymentRepository extends JpaRepository<ObjectPayment, Long> {

	
	ObjectPayment findByIdEquals(Long id);
	ObjectPayment findOneByCode(String code);
	
}
