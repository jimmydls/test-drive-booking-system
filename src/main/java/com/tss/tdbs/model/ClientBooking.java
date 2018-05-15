package com.tss.tdbs.model;

import javax.validation.constraints.NotBlank;

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
	
	@Type( type = "json" )
	@Column(name = "timeslot", columnDefinition = "json" )
	private List<Timeslot> timeslot;
	
	@NotBlank
	@Column(name = "priority")
    private String priority;
	
	@Type( type = "json" )
	@Column(name = "car_model", columnDefinition = "json" )
    private List<String> carModel;
	
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

	public List<Timeslot> getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(List<Timeslot> timeslot) {
		this.timeslot = timeslot;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public List<String> getCarModel() {
		return carModel;
	}

	public void setCarModel(List<String> carModel) {
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
