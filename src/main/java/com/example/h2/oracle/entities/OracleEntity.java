package com.example.h2.oracle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "oracle_user")
public class OracleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oracle_seq")
	@SequenceGenerator(name = "oracle_seq", sequenceName = "oracle_seq", allocationSize = 1)
	private Long id;
	private String name;
	//@Column(name = "my_address", nullable = false)
	@NotNull
	private String myAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMyAddress() {
		return myAddress;
	}

	public void setMyAddress(String myAddress) {
		this.myAddress = myAddress;
	}

}
