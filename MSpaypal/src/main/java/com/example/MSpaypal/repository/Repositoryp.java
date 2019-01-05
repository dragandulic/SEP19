package com.example.MSpaypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MSpaypal.model.Modelp;

@Repository
public interface Repositoryp extends JpaRepository<Modelp, Long>{

}
