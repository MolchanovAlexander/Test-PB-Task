package org.example.testpbtask.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private String lastName;
    private String productName;
    private Integer amount;
}