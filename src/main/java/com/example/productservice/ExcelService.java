package com.example.productservice;

import com.example.productservice.Entity.Product;
import com.example.productservice.Repository.ProductRepository;
import com.example.productservice.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ExcelService
{
    @Autowired
    private ProductRepository repo;

    public ByteArrayInputStream getActualData() throws  Exception
    {
        List<Product> all = repo.findAll();
          ByteArrayInputStream byteArrayInputStream =  Helper.dataToExcel(all);
          return byteArrayInputStream;
    }

}
