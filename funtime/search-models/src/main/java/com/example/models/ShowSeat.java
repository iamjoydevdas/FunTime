package com.example.models;

import com.example.status.SeatType;
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
//Table SHOW_SEATS
public class ShowSeat {
	private Long showSeatId;
	private String status;
	private String seatNo;
	private SeatType seatType;
	private Long bookedSeatId;
	
}
