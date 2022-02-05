package com.example.srchmgnt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.example.srchmgnt.repo.ISearchRepo;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class SearchManagement implements CommandLineRunner
{
	@Autowired
	private ISearchRepo searchRepo;
    public static void main( String[] args )
    {
        SpringApplication.run(SearchManagement.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		searchRepo.getMoviesBySearching();
		
	}
}
