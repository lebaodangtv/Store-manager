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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unit_types")
public class UnitTypes implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 2941540802161338723L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY )
	@Column
	private Long id;
	
	@Column
	private String description;
	
	@Column
	private Boolean isDeleted;
	

}
