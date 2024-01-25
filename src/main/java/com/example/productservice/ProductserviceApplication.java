package com.example.productservice;

import com.example.productservice.Service.mailservice.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@EnableCaching
public class ProductserviceApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(ProductserviceApplication.class, args);
	}


//	@Bean
//	public JavaMailSenderImpl javaMailSender(){
//		return new JavaMailSenderImpl();
//	}

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration
				.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(1))
				.serializeValuesWith(RedisSerializationContext
						.SerializationPair
						.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}


}
