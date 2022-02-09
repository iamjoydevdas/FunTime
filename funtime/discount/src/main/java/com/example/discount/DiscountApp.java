package com.example.discount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
//@EnableFeignClients(basePackages="com.example")
@SpringBootApplication(scanBasePackages="com.example")
public class DiscountApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(DiscountApp.class, args);
    }
}
