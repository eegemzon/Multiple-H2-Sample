package com.example.h2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.h2.mysql.entities.MysqlEntity;
import com.example.h2.mysql.repositories.MysqlEntityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class MysqlTest {

	@TestConfiguration
	static class TestConfig { 
		@Bean
		public DataSourceInitializer mysqlDataSourceInitializer(DataSource mysqlDataSource) {
			DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
			dataSourceInitializer.setDataSource(mysqlDataSource);
			ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
			databasePopulator.addScript(new ClassPathResource("import-mysql.sql"));
			dataSourceInitializer.setDatabasePopulator(databasePopulator);
			return dataSourceInitializer;
		}
	}
	
	@Autowired
	MysqlEntityRepository mysqlEntityRepository;
	
	@Test
	public void testConfig() {
		Optional<MysqlEntity> entity = mysqlEntityRepository.findById(1L);
		assertTrue(entity.isPresent());
	}
	
	@Test
	public void testRepositorySave() {
		Long id = 2L;
		String name = "MysqlName";
		String myAddress = "address";
		MysqlEntity entity = new MysqlEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setMyAddress(myAddress);
		mysqlEntityRepository.save(entity);
		
		Optional<MysqlEntity> result = mysqlEntityRepository.findById(id);
		assertTrue(result.isPresent());
		assertEquals(id, result.get().getId());
		assertEquals(name, result.get().getName());
		assertEquals(myAddress, result.get().getMyAddress());
	}
}
