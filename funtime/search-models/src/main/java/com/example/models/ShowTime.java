package com.example.models;

import java.time.LocalDate;

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
public class ShowTime {
	private Integer movieShowId;
	private LocalDate movieShowDate;
	private String movieShowStartTime;
	private String movieShowEndTime;
	private Integer movieShowScreenId;
	
	private Movie movie;
	
	//private List<ShowSeat> tickets;
}
