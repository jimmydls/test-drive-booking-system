package com.tss.tdbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tss.tdbs.model.EmailNotification;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailNotificationRepository extends JpaRepository<EmailNotification, String>{
	
	public List<EmailNotification> findByStatus(boolean isSuccess );

}
