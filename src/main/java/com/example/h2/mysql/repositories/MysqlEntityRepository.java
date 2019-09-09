package com.example.h2.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.h2.mysql.entities.MysqlEntity;

public interface MysqlEntityRepository extends JpaRepository<MysqlEntity, Long>{

}
