package com.websitebanhang.entitys;

import java.io.Serializable;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unit_types")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UnitTypes implements Serializable {/**
	 * 
	 */
	static final long serialVersionUID = 2941540802161338723L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	Long id;
	
	@Column
	String description;

	@Column
	Boolean isDeleted;
	
}
