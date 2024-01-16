package com.example.productservice.dto;

import com.example.productservice.Entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest
{
    @NotNull(message = "username should not be null")
    private String name;
    @Min(1000)
    @Max(300000)
    private Integer price;
    private String created;
    private String updated;
    private Status status;
}
