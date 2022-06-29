package com.websitebanhang.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Products implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -6646282159245954737L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String name;
	
	@Column 
	private Integer quantity;

	@Column
	private Double price;
	
	@Column
	private String imgUrl;
	
	@Column
	private String slug;
	
	@Column
	private String description;
	
	@Column
	private Boolean isDeleted;
	
	@ManyToOne
	@JsonIgnoreProperties (value = {"applications", "hibernateLazyInitializer"})
	@JoinColumn(name = "typeId", referencedColumnName = "id")
	private ProductTypes productTypes;
	
	@ManyToOne
	@JsonIgnoreProperties (value = {"applications", "hibernateLazyInitializer"})
	@JoinColumn(name = "unitId", referencedColumnName = "id")
	private UnitTypes unitTypes;
	
}

// bi - directionl mapping => mapping 2 chiều (con nhúng cha, cha nhúng list con)
// one - directionl mapping => mapping 1 chiều
