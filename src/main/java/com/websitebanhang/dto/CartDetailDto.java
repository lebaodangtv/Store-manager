package com.websitebanhang.dto;

import java.io.Serializable;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
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
