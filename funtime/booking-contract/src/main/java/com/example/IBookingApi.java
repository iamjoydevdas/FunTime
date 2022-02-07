package com.example;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.payload.Payload;

public interface IBookingApi {
	@GetMapping("/Ok")
	public String ok();
	
	@PostMapping(value="/checkout")
	public String checkout(@RequestBody Payload payload);
}
