package com.example.admin.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.repo.OnboardingRepo;
import com.example.handlers.ResponseHandlers;
import com.example.model.ServiceResponse;
import com.example.models.City;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class Onboarding {
	private final OnboardingRepo onboarding;
	
	@PostMapping("/city")
	public ResponseEntity<ServiceResponse<Long>> onboardNewCity(@RequestBody City city) {
		return new ResponseHandlers<Long>().defaultResponse(onboarding.addNewCity(city));
	}
}
