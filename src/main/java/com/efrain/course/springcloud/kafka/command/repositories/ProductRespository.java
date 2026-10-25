package com.efrain.course.springcloud.kafka.command.repositories;

import com.efrain.course.springcloud.kafka.command.Entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRespository extends CrudRepository<Product, Long> {
}
