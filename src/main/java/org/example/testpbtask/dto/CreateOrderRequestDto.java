package org.example.testpbtask.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateOrderRequestDto {
    @NotBlank
    @Size(min = 2, max = 55)
    private String lastName;
    @NotBlank
    @Size(min = 4, max = 55)
    private String productName;
    @Min(1)
    private Integer amount;
}