package com.tss.tdbs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@NamedQuery(name = "TestDriveScreening.findMatchingTimeslot", query = "SELECT e FROM TestDriveScreening e WHERE e.dateTimeFrom=?1 AND e.carModel=?2 AND e.reserved=?3")
@NamedQuery(name = "TestDriveScreening.findByTimeslot", query = "SELECT e FROM TestDriveScreening e WHERE e.dateTimeFrom=?1 AND e.carModel=?2 AND e.dealerId=?3")
@Table(name = "test_drive_screening")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
allowGetters = true)
public class TestDriveScreening implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "screening_id")
    private Long screening_id;
	
	@Column(name = "dealer_id")
    private Long dealerId;
	
	@Column(name = "car_model")
    private String carModel;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time_from")
    private Date dateTimeFrom;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time_to")
    private Date dateTimeTo;
	
	@Column(name="reserved")
	private boolean reserved;

	public Long getScreening_id() {
		return screening_id;
	}

	public void setScreening_id(Long screening_id) {
		this.screening_id = screening_id;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public Date getDateTimeFrom() {
		return dateTimeFrom;
	}

	public void setDateTimeFrom(Date dateTimeFrom) {
		this.dateTimeFrom = dateTimeFrom;
	}

	public Date getDateTimeTo() {
		return dateTimeTo;
	}

	public void setDateTimeTo(Date dateTimeTo) {
		this.dateTimeTo = dateTimeTo;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}		
	
	

}
