package com.efrain.course.springcloud.kafka.command.services;

import com.efrain.course.springcloud.kafka.command.Entities.Product;
import com.efrain.course.springcloud.kafka.command.models.dto.ProductDto;

public interface ProductService {

    ProductDto create(ProductDto dto);

}
