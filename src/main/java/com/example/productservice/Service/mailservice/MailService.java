package com.example.productservice.Service.mailservice;

import com.example.productservice.Entity.Product;
import com.example.productservice.Repository.ProductRepository;
import com.example.productservice.dto.ProductResponse;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Session;
import java.util.*;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



@Service
public class MailService
{
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    TemplateEngine templateEngine;

    public List<ProductResponse> allData()
    {
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
        return productResponses;
    }

    public void sendSimpleEmail() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("manideept.robocamp@gmail.com");
        message.setTo("manideept.robocamp@gmail.com");
        message.setSubject("subject");
        message.setText(allData().toString());

        mailSender.send(message);
        System.out.println("mail sent successfully");
    }




    public void table(){
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message=new MimeMessageHelper(mimeMessage,"UTF-8");
            message.setTo("manideept.robocamp@gmail.com");
            message.setSubject("test subject");
            List<Product> products= productRepository.findAll();
            Context context=new Context();
            context.setVariable("products",products);
            String data=templateEngine.process("details",context);
            message.setText(data,true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
