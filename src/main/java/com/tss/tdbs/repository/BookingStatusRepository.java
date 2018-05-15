package com.tss.tdbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tss.tdbs.model.Client;

@Repository
public interface BookingStatusRepository extends JpaRepository<Client, String>{

}
