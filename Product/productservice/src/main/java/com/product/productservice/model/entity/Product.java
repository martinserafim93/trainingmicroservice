package com.product.productservice.model.entity;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @NotNull
    private String productName;

    @NotNull
    private String productType;

    @NotNull
    private String productCode;

    @NotNull
    private int productPrice;
}
