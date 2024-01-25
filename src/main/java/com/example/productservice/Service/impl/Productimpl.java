package com.example.productservice.Service.impl;

import com.example.productservice.Entity.Product;
import com.example.productservice.Repository.ProductRepository;
import com.example.productservice.Service.ProductService;
import com.example.productservice.Service.mailservice.MailService;
import com.example.productservice.dto.APIResponse;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.dto.SelectedProductsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Productimpl implements ProductService
{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    MailService mailService;
    @Override
    @Cacheable(value = "product", key = " {#id} ", cacheManager = "cache1")
    public APIResponse addProduct(ProductRequest productRequest)
    {
        Product p = new Product();
        p.setName(productRequest.getName());
        p.setPrice(productRequest.getPrice());
        p.setCreated(productRequest.getCreated());
        p.setUpdated(productRequest.getUpdated());
        p.setStatus(productRequest.getStatus());
        productRepository.save(p);
        System.out.println("hello world");
        //return new APIResponse(HttpStatus.OK.value(), p, "yes successfully added", true);
        return getProduct(p.getId());
    }

    @Override
    @Cacheable(value = "products", key = " 'allProducts' ")
    public APIResponse getProducts() {
        List<Product> products= productRepository.findAll();
        List<ProductResponse> productResponses=new ArrayList<>();
        for(Product product:products)
        {
            ProductResponse productResponse=new ProductResponse();
            productResponse.setCreated(product.getCreated());
            productResponse.setUpdated(product.getUpdated());
            productResponse.setName(product.getName());
            productResponse.setStatus(product.getStatus());
            productResponse.setPrice(product.getPrice());
            productResponses.add(productResponse);
        }
       // mailService.sendSimpleEmail();
       // mailService.table();
        return new APIResponse(HttpStatus.OK.value(), productResponses, "yes successfully fetched data from database", true);
    }




    @Override
    @Cacheable(value = "optional", key = " {#id} ")
    public APIResponse getProduct(Integer id) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> id");
        Optional<Product> optional = productRepository.findById(id);
        ProductResponse productResponse = new ProductResponse();
        Product product = optional.orElse(null);;
        productResponse.setCreated(product.getCreated());
        productResponse.setUpdated(product.getUpdated());
        productResponse.setName(product.getName());
        productResponse.setStatus(product.getStatus());
        productResponse.setPrice(product.getPrice());

        return new APIResponse(HttpStatus.OK.value(), productResponse, "yes successfully fetched data from database", true);

    }

    @Override
    public APIResponse updateProduct(Integer id, ProductRequest productRequest)
    {
        productRepository.findById(id);
        Product p = new Product();
        p.setName(productRequest.getName());
        p.setPrice(productRequest.getPrice());
        p.setCreated(productRequest.getCreated());
        p.setUpdated(productRequest.getUpdated());
        p.setStatus(productRequest.getStatus());
        p.setId(id);
        productRepository.save(p);

        return new APIResponse(HttpStatus.OK.value(), p, "yes successfully updated data", true);
    }

    @Override
    public APIResponse deleteProduct(Integer id)
    {
       productRepository.deleteById(id);
        return new APIResponse(HttpStatus.OK.value(), null, "yes successfully deleted data", true);

    }
    public APIResponse getProductsByIds(SelectedProductsData selectedProductsData)
    {
       return new APIResponse(200,productRepository.findAllById(selectedProductsData.getIds()),"all products of ids",Boolean.TRUE);
    }

}
