package com.tss.tdbs.dto;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class Timeslot implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date testDriveStart;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date testDriveEnd;

	public Date getTestDriveStart() {
		return testDriveStart;
	}

	public void setTestDriveStart(Date testDriveStart) {
		this.testDriveStart = testDriveStart;
	}

	public Date getTestDriveEnd() {
		return testDriveEnd;
	}

	public void setTestDriveEnd(Date testDriveEnd) {
		this.testDriveEnd = testDriveEnd;
	}
	

}
