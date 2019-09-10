package com.example.h2.include;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class TestConfig {
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
