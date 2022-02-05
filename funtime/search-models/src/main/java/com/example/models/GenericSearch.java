package com.example.models;

import java.util.List;
import java.util.Map;

import com.example.payload.Payload;
import com.example.payload.Payload.PayloadBuilder;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class GenericSearch {
	private List<Movie> movie;
	private List<Movie> language;
	private List<Movie> genere;
}
