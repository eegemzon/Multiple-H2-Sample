package com.example.h2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.h2.mysql.entities.MysqlEntity;
import com.example.h2.mysql.repositories.MysqlEntityRepository;
import com.example.h2.oracle.entities.OracleEntity;
import com.example.h2.oracle.repositories.OracleEntityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2SampleApplication.class)
public class H2SampleApplicationTests {

	@Autowired
	MysqlEntityRepository mysqlEntityRepository;
	
	@Autowired
	OracleEntityRepository oracleEntityRepository;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testRepositoryMysql() {
		Long id = 1L;
		String name = "MysqlName";
		String myAddress = "address";
		MysqlEntity entity = new MysqlEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setMyAddress(myAddress);
		mysqlEntityRepository.save(entity);
		
		Optional<MysqlEntity> result = mysqlEntityRepository.findById(1L);
		assertTrue(result.isPresent());
		assertEquals(id, result.get().getId());
		assertEquals(name, result.get().getName());
		assertEquals(myAddress, result.get().getMyAddress());
	}
	
	@Test
	public void testRepositoryOracle() {
		Long id = 1L;
		String name = "MysqlName";
		String myAddress = "address";
		OracleEntity entity = new OracleEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setMyAddress(myAddress);
		oracleEntityRepository.save(entity);
		
		Optional<OracleEntity> result = oracleEntityRepository.findById(1L);
		assertTrue(result.isPresent());
		assertEquals(id, result.get().getId());
		assertEquals(name, result.get().getName());
		assertEquals(myAddress, result.get().getMyAddress());
	}
}
