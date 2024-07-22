package org.example.testpbtask.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateOrderRequestDto {
    @NotBlank
    @NotNull
    @Size(min = 2, max = 55)
    private String lastName;
    @NotBlank
    @NotNull
    @Size(min = 4, max = 55)
    private String productName;
    @Min(1)
    @NotNull
    private Integer amount;
}
