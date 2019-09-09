package com.example.h2.mysql.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mysql_user")
public class MysqlEntity {

	@Id
	private Long id;
	private String name;
	
	@Column(name="my_address", nullable = false)
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
