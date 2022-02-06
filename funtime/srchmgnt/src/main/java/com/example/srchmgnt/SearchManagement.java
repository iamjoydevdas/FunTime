package com.example.srchmgnt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.example.srchmgnt.api.FeignEg;
import com.example.srchmgnt.repo.ISearchRepo;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@EnableFeignClients
@ComponentScan(basePackages="com.example")
@SpringBootApplication
public class SearchManagement implements CommandLineRunner
{
	@Autowired
	private FeignEg searchRepo;
	
    public static void main( String[] args )
    {
        SpringApplication.run(SearchManagement.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		System.out.println(searchRepo.ok());
		searchRepo.ok();
		
	}
}
