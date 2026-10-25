package com.efrain.course.springcloud.kafka.command.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.stream.DoubleStream;


@Entity
@Table(name= "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    private Double price;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime updaetAt;

    @PrePersist
    public void prePersist(){
        createAt = LocalDateTime.now();
        updaetAt =  LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        updaetAt =  LocalDateTime.now();
    }

    public Product() {
    }

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getUpdaetAt() {
        return updaetAt;
    }

    public void setUpdaetAt(LocalDateTime updaetAt) {
        this.updaetAt = updaetAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
