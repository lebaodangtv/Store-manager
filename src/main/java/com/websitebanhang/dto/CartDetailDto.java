package com.websitebanhang.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDto implements Serializable {

	private static final long serialVersionUID = -3557121659199139847L;

	private Long orderId;
	private Long productId;
	private String name;
	private Double price;
	private Integer quantity;
	private String imgUrl;
	private String slug;

}
