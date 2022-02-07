package com.example.booking.crawler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.booking.dto.ICrawlerRepo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ActiveBookingCrawler {
	private final ICrawlerRepo crawlerRepo;
	
	@Scheduled(fixedDelay=1000)
	public void invalidateExceededAwitedBookings() {
		crawlerRepo.invalidateExceededAwitedBookings();
	}

}
