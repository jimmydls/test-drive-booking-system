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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tss.tdbs.dto.Car;
import com.tss.tdbs.dto.DealerAvailability;
import com.tss.tdbs.dto.Timeslot;
import com.vladmihalcea.hibernate.type.json.JsonStringType;


@Entity
@Table(name = "dealer")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
allowGetters = true)
public class Dealer implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dealer_id")
    private Long dealerId;
	
	@NotBlank
	@Column(name = "branch_name")
    private String branchName;
	
	@NotBlank
	@Column(name = "contact_number")
    private String contactNumber;
	
	@NotBlank
	@Column(name = "email")
    private String email;
	
	@NotBlank
	@Column(name = "address1")
    private String address1;
	
	@Column(name = "address2")
    private String address2;
	
	@Column(name = "address3")
    private String address3;
	
	@Column(name = "state")
    private String state;
	
	@Column(name = "postcode")
    private String postCode;
	
	@Column(name = "city")
    private String city;
	
	@Type( type = "json" )
	@Column(name = "car_model", columnDefinition = "json" )
    private List<Car> carModel;
	
	@Type( type = "json" )
	@Column(name = "availability", columnDefinition = "json" )
	private List<DealerAvailability> dealerAvailability;
	
	@Column(name = "group_max_client")
    private int groupMaxClient;
	
	@Column(name = "user_id")
    private String userId;
	
	@Column(name = "password")
    private String password;
	
	@Column(name="created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name="updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

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

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Car> getCarModel() {
		return carModel;
	}

	public void setCarModel(List<Car> carModel) {
		this.carModel = carModel;
	}

	public List<DealerAvailability> getDealerAvailability() {
		return dealerAvailability;
	}

	public void setDealerAvailability(List<DealerAvailability> dealerAvailability) {
		this.dealerAvailability = dealerAvailability;
	}

	public int getGroupMaxClient() {
		return groupMaxClient;
	}

	public void setGroupMaxClient(int groupMaxClient) {
		this.groupMaxClient = groupMaxClient;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
