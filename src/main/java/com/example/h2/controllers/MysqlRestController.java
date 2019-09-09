package com.example.h2.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.h2.mysql.entities.MysqlEntity;
import com.example.h2.mysql.repositories.MysqlEntityRepository;

@RestController
@RequestMapping("/mysql")
public class MysqlRestController {
	
	@Autowired
	private MysqlEntityRepository mysqlEntityRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<MysqlEntity> getMysqlEntity(@PathVariable Long id) {
		System.out.println("Get mysql entity id: " + id);
		Optional<MysqlEntity> optEntity = mysqlEntityRepository.findById(id);
		if(optEntity.isPresent()) {
			return ResponseEntity.ok(optEntity.get());
		} else {
			System.out.println("ID not found.");
			return ResponseEntity.notFound().build();
		}
	}
}
