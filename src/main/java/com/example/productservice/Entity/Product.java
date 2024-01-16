package com.example.productservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer price;
    private String created;
    private String updated;
    @Enumerated(value =  EnumType.STRING)
    private Status status;



}
