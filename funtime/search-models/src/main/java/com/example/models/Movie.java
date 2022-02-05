package com.example.models;

import java.time.LocalDate;

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
public class Movie {
	private Long movieId;
	private String movieTitle;
	private String movieDescription;
	private String duration;
	private String lang;
	private LocalDate releaseDate;
	private String country;
	private String genere;
}
