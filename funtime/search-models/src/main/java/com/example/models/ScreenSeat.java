package com.example.models;

import java.time.LocalDate;
import java.util.List;

import com.example.models.Movie.MovieBuilder;
import com.example.status.SeatType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value=Include.NON_NULL)
public class ScreenSeat {
	private Long seatId;
	private String seatNo;
	private SeatType seatType;
	private String price;
	private List<ShowSeat> bookedSeats;
}
