package com.tss.tdbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tss.tdbs.model.ClientBooking;

@Repository
public interface ClientBookingRepository extends JpaRepository<ClientBooking, Long>{

}
