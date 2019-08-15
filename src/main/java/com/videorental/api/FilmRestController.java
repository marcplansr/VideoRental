package com.videorental.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.videorental.application.FilmController;
import com.videorental.application.dto.FilmDTO;
import com.videorental.utilities.InvalidParamException;
import com.videorental.utilities.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/film")
public class FilmRestController {

	@Autowired
	private FilmController filmController;

	// Saves a new film in Database, and returns the saved object
	// Call from a Rest client http://localhost:8080/film
	@PostMapping(produces = "application/json;charset=UTF-8")
	public String register(@RequestBody String jFilm)
			throws InvalidParamException, NotFoundException {
		FilmDTO newFilm = new Gson().fromJson(jFilm, FilmDTO.class);
		FilmDTO film = filmController.register(newFilm);
		return toJson(film);
	}

	// Returns a list of the films in Database
	// Call from the web browser http://localhost:8080/film
	@GetMapping(produces = "application/json;charset=UTF-8")
	public String listFilms() throws NotFoundException {
		List<FilmDTO> films = filmController.getAllFilms();
		return toJson(films);
	}

	// Returns the film with id = filmId
	// Call from the web browser http://localhost:8080/film/${filmId}
	@GetMapping(value = "/{filmId}", produces = "application/json;charset=UTF-8")
	public String listFilm(@PathVariable UUID filmId) throws NotFoundException {
		FilmDTO film = filmController.getFilmDTO(filmId);
		return toJson(film);
	}

	// Returns a list of the films in Database containing given keyword
	// Call from the web browser http://localhost:8080/film/keyword/${keyword}
	@GetMapping(value = "/keyword/{keyword}", produces = "application/json;charset=UTF-8")
	public String listByKeyWord(@PathVariable String keyword)
			throws NotFoundException {
		List<FilmDTO> films = filmController.getByKeyword(keyword);
		return toJson(films);
	}

	// Updates the film with id = filmId, and returns the updated object
	// Call from the web browser http://localhost:8080/film/${filmId}
	@PutMapping(value = "/{filmId}", produces = "application/json;charset=UTF-8")
	public String UpdateFilm(@PathVariable UUID filmId,
			@RequestBody String jFilm)
			throws NotFoundException, InvalidParamException {
		
		FilmDTO filmUpdateData = new Gson().fromJson(jFilm, FilmDTO.class);

		FilmDTO updatedFilm = filmController.updateFilm(filmId, filmUpdateData);
		return toJson(updatedFilm);
	}

	// Deletes the film with id = filmId, and returns the deleted object
	// Call from a Rest client http://localhost:8080/film/${filmId}
	@DeleteMapping(value = "/{filmId}", produces = "application/json;charset=UTF-8")
	public String DeleteFilm(@PathVariable UUID filmId)
			throws NotFoundException {
		FilmDTO deletedFilm = filmController.deleteFilm(filmId);
		return toJson(deletedFilm);
	}

	// Object to Json converter
	private String toJson(Object object) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		return gson.toJson(object);
	}

}
