package com.example.models;

import java.util.List;

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
public class Screen {
	private Integer screenId;
	private String screenName;
	private Integer totalSeats;
	private Integer screenTheatreId;
	private List<ShowTime> movieShow;
	
	public Screen(Screen s, List<ShowTime> movieShow) {
		this.screenId = s.screenId;
		this.screenName = s.screenName;
		this.totalSeats = s.totalSeats;
		this.screenTheatreId = s.screenTheatreId;
		this.movieShow = movieShow;
	}
}
