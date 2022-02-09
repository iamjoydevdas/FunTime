package com.example.models;

import java.time.LocalDate;
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
public class ShowTime {
	private Long movieShowId;
	private LocalDate movieShowDate;
	private Double movieShowStartTime;
	private Double movieShowEndTime;
	
	private Movie movie;
	private List<Booking> bookings;
}
