package com.example.srchmgnt.repo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.models.City;
import com.example.srchmgnt.repo.ISearchRepo;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class SearchRepo implements ISearchRepo {
	
	private final String ALL_CITY = "select * from city";

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public List<City> getAllCities() {
		List<City> cities = new ArrayList<>();
		jdbcTemplate.query(ALL_CITY, (rs)-> {
			City c = City.builder()
						.name(rs.getString("cityName")).build();
			
			cities.add(c);
		});
		return cities;
	}

}
