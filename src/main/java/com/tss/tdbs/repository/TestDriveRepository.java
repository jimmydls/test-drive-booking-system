package com.tss.tdbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tss.tdbs.model.TestDrive;
import org.springframework.stereotype.Repository;


@Repository
public interface TestDriveRepository extends JpaRepository<TestDrive, Long>{

}
