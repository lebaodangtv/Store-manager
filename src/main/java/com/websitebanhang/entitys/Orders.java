package com.websitebanhang.entitys;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "orders")
public class Orders implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 7299495314531191391L;
	
	@Id
	@Column
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String address;
	
	@Column
	private String phone;
	
	@Column
	@CreationTimestamp
	private Timestamp createdDate;
	
	@ManyToOne
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
	@JoinColumn(name ="userId", referencedColumnName = "id")
	private Users user;
}
