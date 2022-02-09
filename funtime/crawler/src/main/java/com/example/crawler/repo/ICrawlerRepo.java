package com.example.crawler.repo;

import java.util.List;

import com.example.User;

public interface ICrawlerRepo {
	void invalidateExceededAwitedBookings();
	
	List<User> getAllUsersToNotify();
	
	void updateNotificationStatus(Long bookingId);
}
