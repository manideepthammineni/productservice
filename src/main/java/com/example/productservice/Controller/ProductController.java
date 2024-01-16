package com.example.productservice.Controller;

import com.example.productservice.Entity.Product;
import com.example.productservice.Service.ProductService;
import com.example.productservice.dto.APIResponse;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.dto.SelectedProductsData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    private ProductService productservice;

    @PostMapping("/add")
    public APIResponse addProduct(@Valid @RequestBody ProductRequest productRequest)
    {
        return productservice.addProduct(productRequest);

    }
    @GetMapping()
    public APIResponse getProducts()
    {
        return productservice.getProducts();
    }

    @GetMapping("/get")
    public APIResponse getProduct(@RequestParam Integer id)
    {
        return productservice.getProduct(id);
    }
    @PostMapping("/get/products")
    public APIResponse getProductsByIds(@RequestBody SelectedProductsData selectedProductsData){
        return productservice.getProductsByIds(selectedProductsData);
    }


    @PutMapping("/update/{id}")
    public APIResponse updateProduct(@PathVariable Integer id,@RequestBody ProductRequest productRequest)
    {
         return productservice.updateProduct(id,productRequest);

    }

    @DeleteMapping("/delete/{id}")
    public APIResponse deleteProduct(@PathVariable Integer id)
    {
       return productservice.deleteProduct(id);
    }

}
