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
@Table(name = "order_details")
public class OrderDetails implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 6628143525670659156L;
	
	@Id
	@Column
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Double price; 
	
	@Column
	private Integer quantity;
	
	@ManyToOne
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"}) 
	@JoinColumn(name = "orderId", referencedColumnName = "id")
	private Orders orders;
	
	@ManyToOne
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
	@JoinColumn(name ="productId", referencedColumnName = "id")
	private Products products;
}
