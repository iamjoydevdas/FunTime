package com.example.models;

import java.time.LocalDateTime;
import java.util.List;

import com.example.status.BookingStatus;
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
public class Booking {
	private Long bookingId;
	private LocalDateTime bookingTimeStamp;
	private BookingStatus status;
	private Double actualPrice;
	private Integer discount;
	private Double finallPrice;
	private LocalDateTime cancelTimeStamp;
	private List<ShowSeat> bookedSeats;
	private Movie movie;
}
