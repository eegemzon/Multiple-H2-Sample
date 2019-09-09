package com.example.h2.oracle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.h2.oracle.entities.OracleEntity;

public interface OracleEntityRepository extends JpaRepository<OracleEntity, Long>{

}
