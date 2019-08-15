package com.videorental.application.dto;

import java.util.Date;
import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.videorental.domain.Rental;
import com.videorental.utilities.NotFoundException;

public class RentalDTO {

	@Expose
	private UUID id;
	@Expose
	private UUID dvdId;
	@Expose
	private String filmTitle;
	@Expose
	private UUID customerId;
	@Expose
	private String customerFullName;
	@Expose
	private Date startDate;
	@Expose
	private Date dueDate;
	@Expose
	private Date returnDate;
	@Expose
	private Double initialPayment;
	@Expose
	private Double additionalPayment;
	private Integer numberDays;

	// Constructor
	public RentalDTO(Rental rental) throws NotFoundException {
		if (rental == null) {
			throw new NotFoundException();
		}
		this.id = rental.getId();
		dvdId = rental.getDvd().getId();
		filmTitle = rental.getDvd().getFilm().getTitle();
		customerId = rental.getCustomer().getId();
		customerFullName = rental.getCustomer().getName() + " "
				+ rental.getCustomer().getSurnames();
		startDate = rental.getStartDate();
		dueDate = rental.getDueDate();
		returnDate = rental.getReturnDate();
		initialPayment = rental.getInitialPayment();
		additionalPayment = rental.getAdditionalPayment();
	}

	// Getters & setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getDvdId() {
		return dvdId;
	}

	public void setDvdId(UUID dvdId) {
		this.dvdId = dvdId;
	}

	public String getFilmTitle() {
		return filmTitle;
	}

	public void setFilmTitle(String filmTitle) {
		this.filmTitle = filmTitle;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Double getInitialPayment() {
		return initialPayment;
	}

	public void setInitialPayment(Double initialPayment) {
		this.initialPayment = initialPayment;
	}

	public Double getAdditionalPayment() {
		return additionalPayment;
	}

	public void setAdditionalPayment(Double additionalPayment) {
		this.additionalPayment = additionalPayment;
	}

	public Integer getNumberDays() {
		return numberDays;
	}

	public void setNumberDays(Integer numberDays) {
		this.numberDays = numberDays;
	}

}
