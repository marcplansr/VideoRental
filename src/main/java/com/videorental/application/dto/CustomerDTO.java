package com.videorental.application.dto;

import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.videorental.utilities.NotFoundException;
import com.videorental.domain.Customer;

public class CustomerDTO {

	@Expose
	private UUID id;
	@Expose
	private String name;
	@Expose
	private String surnames;
	@Expose
	private String email;
	@Expose
	private String phone;
	@Expose
	private Integer bonusPoints;

	// Constructor
	public CustomerDTO(Customer customer) throws NotFoundException {
		if (customer == null) {
			throw new NotFoundException();
		}
		this.id = customer.getId();
		this.name = customer.getName();
		this.surnames = customer.getSurnames();
		this.email = customer.getEmail();
		this.phone = customer.getPhone();
		this.bonusPoints = customer.getBonusPoints();
	}

	// Getters & setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(Integer bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

}
