package com.example.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.booking.dto.impl.BookingRepo;


@EnableFeignClients(basePackages="com.example")
@EnableAspectJAutoProxy(proxyTargetClass=true)  
@SpringBootApplication(scanBasePackages= {"com.example", "com.example.booking.crawler"})
public class BookingManagement implements CommandLineRunner
{
	@Autowired
	private BookingRepo repo;
	
    public static void main( String[] args )
    {
       SpringApplication.run(BookingManagement.class, args);
    }
    
	@Override
	public void run(String... args) throws Exception {
		
	}
}
