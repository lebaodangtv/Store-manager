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

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Users implements Serializable {/**
	 * 
	 */
	static final long serialVersionUID = -7866646107417363856L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column
	@Size(max = 20, message = "Username must be less than 20 characters")
	String username;
	
	@Column 
	@Size(max = 50, message = "Fullname must be less than 50 characters")
	String fullname;
	
	@Column 
	String hashPassword;
	
	@Column
	@Email
	String email;
	
	@Column 
	@CreationTimestamp // lấy thời gian hệ thống ngây tại thời điểm insert entitys vào database
	Timestamp createdDate;
	
	@Column
	String imgUrl;
	
	@Column
	Boolean isDeleted;
	
	@ManyToOne
	@JsonIgnoreProperties (value = {"applications", "hibernateLazyInitializer"})
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	Roles roles;
	
}
