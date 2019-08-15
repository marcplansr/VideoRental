package com.videorental.application.dto;

import java.util.Date;
import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.videorental.domain.Dvd;
import com.videorental.domain.enums.DvdStatus;
import com.videorental.utilities.NotFoundException;

public class DvdDTO {
	
	@Expose
	private UUID id;
	private UUID filmId;
	@Expose
	private String filmTitle;
	private Date dvdDate;
	@Expose
	private DvdStatus status;
	@Expose
	private Date dueDate;
	
	// Constructor
	public DvdDTO(Dvd dvd) throws NotFoundException {
		if (dvd == null) {
			throw new NotFoundException();
		}
		this.id = dvd.getId();
		this.filmTitle = dvd.getFilm().getTitle();
		this.dvdDate = dvd.getDvdDate();
		this.status = dvd.getStatus();
	}

	// Getters & setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getFilmId() {
		return filmId;
	}

	public void setFilmId(UUID filmId) {
		this.filmId = filmId;
	}

	public String getFilmTitle() {
		return filmTitle;
	}

	public void setFilmTitle(String filmTitle) {
		this.filmTitle = filmTitle;
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

}
