package com.tss.tdbs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tss.tdbs.model.TestDrive;

import org.springframework.stereotype.Repository;


@Repository
public interface TestDriveRepository extends JpaRepository<TestDrive, Long>{
	
	public List<TestDrive> findExistTestDrive(Long dealerId, String carModel, Date dateTimeFrom);
	public TestDrive findByBookingId(Long bookingId);
	public TestDrive findByDealerId(Long dealerId);

}
