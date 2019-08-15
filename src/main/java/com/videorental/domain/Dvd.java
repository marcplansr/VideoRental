package com.videorental.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.videorental.domain.enums.DvdStatus;

@Entity(name = "dvd")
public class Dvd {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;

	@Column(name = "dvd_date")
	private Date dvdDate;
	
	@Enumerated(EnumType.STRING)
	private DvdStatus status;
	
	// Constructor
	protected Dvd() {}

	public Dvd(Film film) {
		this.film = film;
		this.dvdDate = new Date();
		this.status = DvdStatus.AVAILABLE;
	}

	// Getters & setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Date getDvdDate() {
		return dvdDate;
	}

	public void setDvdDate(Date dvdDate) {
		this.dvdDate = dvdDate;
	}

	public DvdStatus getStatus() {
		return status;
	}

	public void setStatus(DvdStatus status) {
		this.status = status;
	}

}
