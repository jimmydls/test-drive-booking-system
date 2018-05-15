package com.tss.tdbs.dto;

import java.io.Serializable;

public class DealerAvailability implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int day;
	
	private int from;
	
	private int to;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}
	

}
