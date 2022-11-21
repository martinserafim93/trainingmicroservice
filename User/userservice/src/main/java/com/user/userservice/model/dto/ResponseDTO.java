package com.user.userservice.model.dto;


import lombok.Data;

@Data
public class ResponseDTO {

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String phoneNumber;

    private ProductDTO productDTO;
}
