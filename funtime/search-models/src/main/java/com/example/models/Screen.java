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
	private Long screenId;
	private String screenName;
	private List<ShowTime> movieShow;
	private Long cinemaHallId;
	private List<ScreenSeat> seats;
	
	public Screen(Screen s, List<ShowTime> movieShow) {
		this.screenId = s.screenId;
		this.screenName = s.screenName;
		this.movieShow = movieShow;
	}
}
