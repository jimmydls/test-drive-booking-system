package com.tss.tdbs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tss.tdbs.model.TestDriveScreening;

@Repository
public interface TestDriveScreeningRepository extends JpaRepository<TestDriveScreening, Long>{
	
	public List<TestDriveScreening> findMatchingTimeslot(Date dateTimeFrom, String carModel, boolean isReserved );
	public TestDriveScreening findByTimeslot(Date dateTimeFrom, String carModel, Long dealerId);

}
