package com.example.h2;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
		basePackages = "com.example.h2.mysql.repositories", 
		entityManagerFactoryRef = "mysqlEntityManager", 
		transactionManagerRef = "mysqlTransactionManager")
public class MysqlConfig {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
	@Value("${spring.mysql.jpa.properties.hibernate.dialect}")
	private String dialect;

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.mysql.datasource")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name="mysqlEntityManager")
	public LocalContainerEntityManagerFactoryBean mysqlEntityManager(EntityManagerFactoryBuilder builder) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", ddlAuto);
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
		return builder.dataSource(mysqlDataSource()).properties(properties)
				.packages("com.example.h2.mysql.entities").persistenceUnit("mysql").build();
	}

	@Primary
	@Bean
	public PlatformTransactionManager mysqlTransactionManager(
			@Qualifier("mysqlEntityManager") EntityManagerFactory mysqlEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(mysqlEntityManager);
		return transactionManager;
	}
	//try to move to testing because it is not used in real app, only for running with h2, 
		//comment out if testing
	/*@Primary
	@Bean
	public DataSourceInitializer mysqlDataSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(mysqlDataSource());
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("import-mysql.sql"));
		dataSourceInitializer.setDatabasePopulator(databasePopulator);
		return dataSourceInitializer;
	}*/
}
