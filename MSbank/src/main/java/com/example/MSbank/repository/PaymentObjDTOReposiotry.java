package com.example.MSbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MSbank.dto.PaymentObjDTO;

@Repository
public interface PaymentObjDTOReposiotry extends JpaRepository<PaymentObjDTO, Long> {

}
