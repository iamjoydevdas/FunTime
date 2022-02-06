package com.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="booking-management")
public interface BookingFeign {
	@GetMapping("/Ok")
	public String ok();
}
