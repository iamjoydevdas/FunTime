package com.example.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class BookingManagement 
{
    public static void main( String[] args )
    {
       SpringApplication.run(BookingManagement.class, args);
    }
}
