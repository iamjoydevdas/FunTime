package com.example.crawler;

import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.User;
import com.example.crawler.repo.ICrawlerRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@Service
@AllArgsConstructor
public class NotificationCrawler {
	private final ICrawlerRepo crawlerRepo;
		
	@Scheduled(fixedDelay=1000)
	public void notifyUsers() {
		 List<User> allUsersToNotify = crawlerRepo.getAllUsersToNotify();
		 
		 /**
		  * 
		  * Send notfication to users via email and text
		  *   
		  * 
		  */
	}
	
}
