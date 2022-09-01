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
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "users")
public class Users implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -7866646107417363856L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Size(max = 20, message = "Username must be less than 20 characters")
	private String username;
	
	@Column 
	@Size(max = 50, message = "Fullname must be less than 50 characters")
	private String fullname;
	
	@Column 
	private String hashPassword;
	
	@Column
	@Email
	private String email;
	
	@Column 
	@CreationTimestamp // lấy thời gian hệ thống ngây tại thời điểm insert entitys vào database
	private Timestamp createdDate; 
	
	@Column
	private String imgUrl;
	
	@Column
	private Boolean isDeleted;
	
	@ManyToOne
	@JsonIgnoreProperties (value = {"applications", "hibernateLazyInitializer"})
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	private Roles roles;
	
}
