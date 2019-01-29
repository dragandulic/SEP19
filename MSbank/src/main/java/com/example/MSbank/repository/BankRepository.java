package com.example.MSbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MSbank.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

	Bank findByBanknumberEquals(String s);
}
