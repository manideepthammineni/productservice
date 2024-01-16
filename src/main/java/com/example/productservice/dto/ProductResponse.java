package com.example.productservice.dto;

import com.example.productservice.Entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse
{
    private String name;
    private Integer price;
    private String created;
    private String updated;
    private Status status;
}
