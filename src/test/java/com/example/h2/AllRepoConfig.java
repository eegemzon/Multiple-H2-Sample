package com.example.h2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.h2.include.TestConfig;
import com.example.h2.mysql.entities.MysqlEntity;
import com.example.h2.mysql.repositories.MysqlEntityRepository;
import com.example.h2.oracle.entities.OracleEntity;
import com.example.h2.oracle.repositories.OracleEntityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Import(TestConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
public class AllRepoConfig {
	
	@Autowired
	OracleEntityRepository oracleEntityRepository;
	
	@Autowired
	MysqlEntityRepository mysqlEntityRepository;
	
	@Test
	public void testMysqlConfig() {
		Optional<OracleEntity> entity = oracleEntityRepository.findById(1L);
		assertTrue(entity.isPresent());
	}
	
	@Test
	public void testOracleConfig() {
		Optional<MysqlEntity> entity = mysqlEntityRepository.findById(1L);
		assertTrue(entity.isPresent());
	}
	
	@Test 
	public void testSave() {
		String name = "name";
		String myAddress = "address";
		OracleEntity entity = new OracleEntity();
		entity.setName(name);
		entity.setMyAddress(myAddress);
		entity = oracleEntityRepository.save(entity);
		System.out.println("ID generated: " + entity.getId());
		Optional<OracleEntity> result = oracleEntityRepository.findById(entity.getId());
		assertTrue(result.isPresent());
		assertEquals(name, result.get().getName());
		assertEquals(myAddress, result.get().getMyAddress());
	}
}
