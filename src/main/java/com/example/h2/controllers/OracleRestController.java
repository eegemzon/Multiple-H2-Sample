package com.example.h2.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.h2.oracle.entities.OracleEntity;
import com.example.h2.oracle.repositories.OracleEntityRepository;

@RestController
@RequestMapping("/oracle")
public class OracleRestController {
	
	@Autowired
	private OracleEntityRepository oracleEntityRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<OracleEntity> getOracleEntity(@PathVariable Long id) {
		System.out.println("Get oracle entity id: " + id);
		Optional<OracleEntity> optEntity = oracleEntityRepository.findById(id);
		if(optEntity.isPresent()) {
			return ResponseEntity.ok(optEntity.get());
		} else {
			System.out.println("ID not found.");
			return ResponseEntity.notFound().build();
		}
	}
}
