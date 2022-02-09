package com.example.models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(value=Include.NON_NULL)
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CinemaHall {
	private Long cinemaHallId;
	private String cinemaHallShortName;
	private String cinemaHallName;
	private String favCity;
	private Long cityId;

	private List<Screen> screens;
	
	
	public CinemaHall(CinemaHall ch, Map<Screen, List<ShowTime>>  screens) {
		this.cinemaHallId = ch.cinemaHallId;
		this.cinemaHallName = ch.cinemaHallName;
		this.cinemaHallShortName = ch.cinemaHallShortName;
		
		this.screens = screens.entrySet().stream().map(entry -> new Screen(entry.getKey(),entry.getValue())).collect(Collectors.toList());
	}
}
