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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer price;
    private String created;
    private String updated;
    @Enumerated(value =  EnumType.STRING)
    private Status status;


    public Product(String name,Integer price,String created, String updated, Status status)
    {
        this.name = name;
        this.price = price;
        this.created = created;
        this.updated = updated;
        this.status = status;
    }

}
