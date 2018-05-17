package com.tss.tdbs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@Entity
@NamedQuery(name = "TestDrive.findExistTestDrive", query = "SELECT e FROM TestDrive e WHERE e.dealerId=?1 AND e.carModel=?2 AND e.dateTimeFrom=?3 AND e.statusId!='DC'")
@NamedQuery(name = "TestDrive.findByBookingId", query = "SELECT e FROM TestDrive e WHERE e.bookingId=?1")
@Table(name = "test_drive")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
allowGetters = true)
public class TestDrive implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "test_drive_id")
    private Long testDriveId;
	
	//@Type( type = "json" )
	//@NotBlank
	//@Column(name = "booking_id", columnDefinition = "json" )
    //private List<Long> bookingId;
	
	@Column(name = "booking_id")
    private Long bookingId;
	
	@Column(name = "dealer_id")
    private Long dealerId;
	
	@Column(name = "screening_id")
    private Long screeningId;
	
	/*@Type( type = "json" )
	@NotBlank
	@Column(name = "car_model", columnDefinition = "json" )
    private List<String> carModel;
	*/
	
	@NotBlank
	@Column(name = "car_model")
	private String carModel;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time_from")
    private Date start;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time_to")
    private Date end;
	
	@Column(name = "title")
	private String title;
	
	@NotBlank
	@Column(name = "status_id")
    private String statusId;
	
	@Column(name="created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name="updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
    
    @Column(name = "prefer_group")
    private boolean preferGroup;

	public Long getTestDriveId() {
		return testDriveId;
	}

	public void setTestDriveId(Long testDriveId) {
		this.testDriveId = testDriveId;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}
		

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getScreeningId() {
		return screeningId;
	}

	public void setScreeningId(Long screeningId) {
		this.screeningId = screeningId;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isPreferGroup() {
		return preferGroup;
	}

	public void setPreferGroup(boolean preferGroup) {
		this.preferGroup = preferGroup;
	}
    
	
    
}
