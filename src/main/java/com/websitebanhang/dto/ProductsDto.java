package com.websitebanhang.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductsDto {
    Long id;
    String productName;
    String name;
    Double price;
    String typeName;
    Integer quantity;
    String slug;
    String description;
    Long stt;
}
