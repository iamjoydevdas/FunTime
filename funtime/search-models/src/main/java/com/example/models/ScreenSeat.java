package com.example.models;

import java.time.LocalDate;
import java.util.List;

import com.example.models.Movie.MovieBuilder;
import com.example.status.SeatType;

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
public class ScreenSeat {
	private Long seatId;
	private String seatNo;
	private SeatType seatType;
	private List<ShowSeat> bookedSeats;
}
