package com.example.crawler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.crawler.repo.ICrawlerRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@Service
@AllArgsConstructor
public class ActiveBookingCrawler {
	
	private final ICrawlerRepo crawlerRepo;
	
	@Scheduled(fixedDelay=1)
	public void invalidateExceededAwitedBookings() {
		 log.info("I am running"); 
		 crawlerRepo.invalidateExceededAwitedBookings(); 
	}
	
}
