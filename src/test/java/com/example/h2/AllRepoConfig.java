package com.example.h2;

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
import com.example.h2.oracle.entities.OracleEntity;
import com.example.h2.oracle.repositories.OracleEntityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class AllRepoConfig {

	@TestConfiguration
	static class TestConfig {
		@Bean
		public DataSourceInitializer oracleDataSourceInitializer(DataSource oracleDataSource) {
			DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
			dataSourceInitializer.setDataSource(oracleDataSource);
			ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
			databasePopulator.addScript(new ClassPathResource("import-oracle.sql"));
			dataSourceInitializer.setDatabasePopulator(databasePopulator);
			return dataSourceInitializer;
		}

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
	
}
