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

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "orders")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders implements Serializable{/**
	 * 
	 */
	static final long serialVersionUID = 7299495314531191391L;
	
	@Id
	@Column
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column
	String address;
	
	@Column
	String phone;
	
	@Column
	@CreationTimestamp
	Timestamp createdDate;
	
	@ManyToOne
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
	@JoinColumn(name ="userId", referencedColumnName = "id")
	Users user;
}
