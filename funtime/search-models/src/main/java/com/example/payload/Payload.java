package com.example.payload;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payload {
	private String searchText;
	private Long cityId;
	private Long movieId;
	private Long cinemaHallId;
	private Long screenId;
	private List<Long> seats;
	private Long userId;
	private Long movieShowTimeId;
}
