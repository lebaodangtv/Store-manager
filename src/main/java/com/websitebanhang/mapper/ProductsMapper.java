package com.websitebanhang.mapper;

import com.websitebanhang.dto.ProductsDto;
import com.websitebanhang.entitys.Products;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductsMapper {
    List<ProductsDto> toDTO(List<Products> product);
}
