package com.example.grocery.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private long customerId;

	@NotNull
	@Column(name = "customer_name")
	private String customerName;

	@NotNull
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "address")
	private String address;

	@NotNull
	@Column(name = "contactNumber")
	private String contactNumber;

	@NotNull
	@Column(name = "emailDomain")
	private String emailDomain;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(String activeUser) {
		this.activeUser = activeUser;
	}

	@NotNull
	@Column(name = "activeUser")
	private String activeUser;

	public Customer(long customerId, @NotNull String customerName, @NotNull String email, @NotNull String password,
			@NotNull String address, @NotNull String contactNumber, @NotNull String emailDomain,
			@NotNull String activeUser) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.contactNumber = contactNumber;
		this.emailDomain = emailDomain;
		this.activeUser = activeUser;
	}

	public Customer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", email=" + email
				+ ", password=" + password + ", address=" + address + ", contactNumber=" + contactNumber
				+ ", emailDomain=" + emailDomain + ", activeUser=" + activeUser + "]";
	}
	
	

}
