package com.example.productservice.Service;

import com.example.productservice.dto.APIResponse;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.SelectedProductsData;

public interface ProductService {
    APIResponse addProduct(ProductRequest productRequest);

    APIResponse getProducts();

    APIResponse getProductsByIds(SelectedProductsData selectedProductsData );

    APIResponse getProduct(Integer id);

    APIResponse updateProduct(Integer id, ProductRequest productRequest);

    APIResponse deleteProduct(Integer id);

}
