package com.example.srchmgnt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.example.BookingFeign;

/**
 * Hello world!
 *
 */
@EnableFeignClients(basePackages="com.example")
@ComponentScan(basePackages="com.example")
@SpringBootApplication
public class SearchManagement implements CommandLineRunner
{
	@Autowired
	private BookingFeign searchRepo;
	
    public static void main( String[] args )
    {
        SpringApplication.run(SearchManagement.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		System.out.println(searchRepo.ok());
	}
}
