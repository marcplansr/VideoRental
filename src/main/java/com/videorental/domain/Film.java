package com.videorental.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.videorental.domain.enums.FilmType;

@Entity(name = "film")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;
	private String title;
	private Integer year;
	private String director;
	@Enumerated(EnumType.STRING)
	private FilmType type;

	// Constructors
	protected Film() {	}

	public Film(String title, Integer year, String director, FilmType type) {
		this.title = title;
		this.year = year;
		this.director = director;
		this.type = type;
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
