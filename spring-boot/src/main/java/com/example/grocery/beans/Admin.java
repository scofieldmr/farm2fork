package com.example.grocery.beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Table(name = "admin")
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private long adminId;

    @NotNull
    @Column(name = "admin_name")
    private String adminName;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "address")
    private String address;

    public long getAdminId() {
		return adminId;
	}


	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
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

	public Admin() {
		super();
	}

	public Admin(long adminId, @NotNull String adminName, @NotNull String email, @NotNull String password,
			@NotNull String address, @NotNull String contactNumber, @NotNull String emailDomain,
			@NotNull String activeUser) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.contactNumber = contactNumber;
		this.emailDomain = emailDomain;
		this.activeUser = activeUser;
	}

	@NotNull
    @Column(name = "contactNumber")
    private String contactNumber;

    @NotNull
    @Column(name = "emailDomain")
    private String emailDomain;

    @NotNull
    @Column(name = "activeUser")
    private String activeUser;

}
