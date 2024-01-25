package com.example.productservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
/*
@Service
public class Producer
{
    @Autowired
     KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessageToTopic(String message)
    {
         kafkaTemplate.send("productDetails", message);
    }




}
*/