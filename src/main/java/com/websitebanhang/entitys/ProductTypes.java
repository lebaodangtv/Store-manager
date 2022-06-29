package com.websitebanhang.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_types")
public class ProductTypes implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -5735279774288728642L;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String description;
	
	@Column
	private String slug;
	
	@Column
	private Boolean isDeleted;
	
}
