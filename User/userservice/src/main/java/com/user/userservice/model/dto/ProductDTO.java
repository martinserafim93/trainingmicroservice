package com.user.userservice.model.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private int productId;

    private String productName;

    private String productType;

    private String productCode;

    private int productPrice;
}
