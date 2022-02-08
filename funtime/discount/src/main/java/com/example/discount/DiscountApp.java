package com.example.discount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication(scanBasePackages="com.example")
public class DiscountApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(DiscountApp.class, args);
    }
}
