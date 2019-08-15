package com.videorental.application.dto;

import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.videorental.domain.Film;
import com.videorental.domain.enums.FilmType;
import com.videorental.utilities.NotFoundException;

public class FilmDTO {

	@Expose
	private UUID id;
	@Expose
	private String title;
	@Expose
	private Integer year;
	@Expose
	private String director;
	@Expose
	private FilmType type;

	// Constructor
	public FilmDTO(Film film) throws NotFoundException {
		if (film == null) {
			throw new NotFoundException();
		}
		this.id = film.getId();
		this.title = film.getTitle();
		this.year = film.getYear();
		this.director = film.getDirector();
		this.type = film.getType();
	}

	// Getters & setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public FilmType getType() {
		return type;
	}

	public void setType(FilmType type) {
		this.type = type;
	}

}
