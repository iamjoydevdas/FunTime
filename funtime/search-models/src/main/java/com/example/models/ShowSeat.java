package com.example.models;

import java.util.List;

import com.example.models.Screen.ScreenBuilder;
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
	private Long bookedSeatId;
}
