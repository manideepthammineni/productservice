package com.example.productservice.Controller;

import com.example.productservice.ExcelService;
//import com.example.productservice.Service.Producer;
import com.example.productservice.Service.ProductService;
import com.example.productservice.dto.APIResponse;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.SelectedProductsData;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    private ProductService productservice;
    /*@Autowired
    Producer producer;*/

    @Autowired
    private ExcelService excelService;

    @PostMapping("/add")
    public APIResponse addProduct(@Valid @RequestBody ProductRequest productRequest)
    {
        return productservice.addProduct(productRequest);


    }
    @GetMapping("/calculate")
    @Cacheable(value = "calculate",key = " 'calculate'")
    public String calculateResult(@RequestParam("input") String input)
    {
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return "billion dollar company " + input;
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

@SneakyThrows
@RequestMapping("/excel")
    public ResponseEntity<InputStreamResource> download()
    {
       String fileName = "products.xlsx";
       ByteArrayInputStream actualData = excelService.getActualData();
        InputStreamResource file = new InputStreamResource(actualData);
              ResponseEntity<InputStreamResource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

              return body;
    }

@GetMapping("/producerMessage")
    public void getMessageFromClient(@RequestParam("message") String message)
    {
        //producer.sendMessageToTopic(message);
    }

}