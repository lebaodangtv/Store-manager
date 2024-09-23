package com.websitebanhang.entitys;

import java.io.Serializable;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_types")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductTypes implements Serializable {/**
	 * 
	 */
	static final long serialVersionUID = -5735279774288728642L;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column
	Long id;
	
	@Column
	String description;
	
	@Column
	String name;
	
	@Column
	String slug;
	
	@Column
	Boolean isDeleted;
	
}
