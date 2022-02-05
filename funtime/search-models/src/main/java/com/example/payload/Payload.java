package com.example.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payload {
	private String searchText;
}
