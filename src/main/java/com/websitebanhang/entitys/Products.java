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

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Products implements Serializable {/**
	 * 
	 */
	static final long serialVersionUID = -6646282159245954737L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	Long id;
	
	@Column
	String name;
	
	@Column 
	Integer quantity;

	@Column
	Double price;
	
	@Column
	String imgUrl;
	
	@Column
	String slug;
	
	@Column
	String description;
	
	@Column
	Boolean isDeleted;
	
	@ManyToOne
	@JsonIgnoreProperties (value = {"applications", "hibernateLazyInitializer"})
	@JoinColumn(name = "typeId", referencedColumnName = "id")
	ProductTypes productTypes;
	
	@ManyToOne
	@JsonIgnoreProperties (value = {"applications", "hibernateLazyInitializer"})
	@JoinColumn(name = "unitId", referencedColumnName = "id")
	UnitTypes unitTypes;
	
}

// bi - directionl mapping => mapping 2 chiều (con nhúng cha, cha nhúng list con)
// one - directionl mapping => mapping 1 chiều
