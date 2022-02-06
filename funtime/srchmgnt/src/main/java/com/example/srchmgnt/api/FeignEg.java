package com.example.srchmgnt.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="booking-management")
public interface FeignEg {
	@GetMapping("/Ok")
	public String ok();
}
