package com.example;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
//@AllArgsConstructor
@Service
public class ActiveBookingCrawler {
	//private final ICrawlerRepo crawlerRepo;
	
	@Scheduled(fixedDelay=1000)
	public void invalidateExceededAwitedBookings() {
		log.info("I am running");
		//crawlerRepo.invalidateExceededAwitedBookings();
	}
	@Scheduled(fixedRate=1000)
	public void invalidateExceededAwitedBookingsq() {
		log.info("I am running 1");
		//crawlerRepo.invalidateExceededAwitedBookings();
	}
	
	@Scheduled(fixedDelay=1)
	public void invalidateExceededAwitedBookings1() {
		log.info("I am running");
		//crawlerRepo.invalidateExceededAwitedBookings();
	}
	@Scheduled(fixedRate=1)
	public void invalidateExceededAwitedBookingsq1() {
		log.info("I am running 1");
		//crawlerRepo.invalidateExceededAwitedBookings();
	}
	
	@Scheduled(fixedDelay=10)
	public void invalidateExceededAwitedBookings3() {
		log.info("I am running");
		//crawlerRepo.invalidateExceededAwitedBookings();
	}
	@Scheduled(fixedRate=10)
	public void invalidateExceededAwitedBookingsq3() {
		log.info("I am running 1");
		//crawlerRepo.invalidateExceededAwitedBookings();
	}
}
