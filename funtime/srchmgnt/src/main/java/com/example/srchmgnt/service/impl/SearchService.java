package com.example.srchmgnt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.models.City;
import com.example.srchmgnt.repo.impl.SearchRepo;
import com.example.srchmgnt.service.ISearchService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SearchService implements ISearchService {
	private final SearchRepo searchRepo;
	
	@Override
	public List<City> getAllCities() {
		// TODO Auto-generated method stub
		return searchRepo.getAllCities();
	}

}
