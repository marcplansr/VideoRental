package com.videorental.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "rental")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;
	@OneToOne
	@JoinColumn(name = "dvd_id")
	private Dvd dvd;
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "due_date")
	private Date dueDate;
	@Column(name = "return_date")
	private Date returnDate;
	@Column(name = "initial_payment")
	private Double initialPayment;
	@Column(name = "additional_payment")
	private Double additionalPayment;

	// Constructors
	protected Rental() {
	}

	public Rental(Dvd dvd, Customer customer, Date startDate,
			Date dueDate, Double initialPayment) {
		this.dvd = dvd;
		this.customer = customer;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.initialPayment = initialPayment;
		additionalPayment = 0.0;
	}

	// Getters & setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Dvd getDvd() {
		return dvd;
	}

	public void setDvd(Dvd dvd) {
		this.dvd = dvd;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	
}
