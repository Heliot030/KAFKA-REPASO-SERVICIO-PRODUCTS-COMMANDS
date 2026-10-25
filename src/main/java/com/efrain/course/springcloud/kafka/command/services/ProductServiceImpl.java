package com.efrain.course.springcloud.kafka.command.services;

import com.efrain.course.springcloud.kafka.command.Entities.Product;
import com.efrain.course.springcloud.kafka.command.models.dto.ProductDto;
import com.efrain.course.springcloud.kafka.command.models.mapper.Mappers;
import com.efrain.course.springcloud.kafka.command.repositories.ProductRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRespository productRespository;

    public ProductServiceImpl(ProductRespository productRespository) {
        this.productRespository = productRespository;
    }

    @Override
    @Transactional
    public ProductDto create(ProductDto dto) {
        //Product product = new Product(dto.name(),dto.price());
        Product productNew = productRespository.save(Mappers.toEntity(dto));
        return Mappers.toDto(productNew);
    }
}
