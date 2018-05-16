package com.tss.tdbs.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import com.tss.tdbs.dto.Timeslot;

@Entity
@NamedQuery(name = "ClientBooking.findByStatus", query = "SELECT e FROM ClientBooking e WHERE e.statusId = ?1 ORDER BY e.priority")
@Table(name = "client_booking")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
allowGetters = true)
public class ClientBooking implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
    private Long bookingId;
	
	//@Type( type = "json" )
	//@Column(name = "timeslot", columnDefinition = "json" )
	//private List<Timeslot> timeslot;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateTimeFrom")
    private Date dateTimeFrom;
	
	@Column(name = "priority")
    private int priority;
	
	//@Type( type = "json" )
	//@Column(name = "car_model", columnDefinition = "json" )
    //private List<String> carModel;
	
	@NotBlank
	@Column(name = "car_model")
    private String carModel;
	
	@Column(name = "prefer_group")
    private boolean preferGroup;
	
	@NotBlank
	@Column(name = "client_id")
    private String clientId;
	
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

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}	

	public Date getDateTimeFrom() {
		return dateTimeFrom;
	}

	public void setDateTimeFrom(Date dateTimeFrom) {
		this.dateTimeFrom = dateTimeFrom;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public boolean isPreferGroup() {
		return preferGroup;
	}

	public void setPreferGroup(boolean preferGroup) {
		this.preferGroup = preferGroup;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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
	
}
