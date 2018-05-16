package com.tss.tdbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tss.tdbs.model.ClientBooking;
import com.tss.tdbs.model.EmailNotification;

@Repository
public interface ClientBookingRepository extends JpaRepository<ClientBooking, Long>{
	
	public List<ClientBooking> findByStatus(String statusId );

}
