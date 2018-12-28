package com.example.MSbitcoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MSbitcoin.model.Proba;

@Repository
public interface ProbaRepository extends JpaRepository<Proba, Long> {

}
