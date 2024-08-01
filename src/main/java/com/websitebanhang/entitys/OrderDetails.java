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
@Table(name = "order_details")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetails implements Serializable {/**
	 * 
	 */
	static final long serialVersionUID = 6628143525670659156L;
	
	@Id
	@Column
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column
	Double price;
	
	@Column
	Integer quantity;
	
	@ManyToOne
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"}) 
	@JoinColumn(name = "orderId", referencedColumnName = "id")
	Orders orders;
	
	@ManyToOne
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
	@JoinColumn(name ="productId", referencedColumnName = "id")
	Products products;
}
