package com.example.srchmgnt.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.City;
import com.example.srchmgnt.service.impl.SearchService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class SearchController {
	
	private final SearchService searchService;
	
	@GetMapping("/city")
	public ResponseEntity<List<City>> allCity() {
		return new ResponseEntity<>(searchService.getAllCities(), HttpStatus.OK);
	}
}
