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
import com.videorental.application.DvdController;
import com.videorental.application.dto.DvdDTO;
import com.videorental.utilities.InvalidParamException;
import com.videorental.utilities.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/dvd")
public class DvdRestController {

	@Autowired
	private DvdController dvdController;
	
	// Saves a new dvd in Database, and returns the saved object
	// Call from a Rest client http://localhost:8080/dvd
	@PostMapping(produces = "application/json;charset=UTF-8")
	public String register(@RequestBody String jDvd)
			throws InvalidParamException, NotFoundException {
		DvdDTO newDvd = new Gson().fromJson(jDvd, DvdDTO.class);
		DvdDTO dvd = dvdController.register(newDvd);
		return toJson(dvd);
	}

	// Returns a list of dvds in Database grouped and sorted by title
	// Call from the web browser http://localhost:8080/dvd
	@GetMapping(produces = "application/json;charset=UTF-8")
	public String listAllDvds() throws NotFoundException {
		List<DvdDTO> dvds = dvdController.getAllDvds();
		return toJson(dvds);
	}

	// Returns the dvd with id = dvdId
	// Call from the web browser http://localhost:8080/dvd/${dvdId}
	@GetMapping(value = "/{dvdId}", produces = "application/json;charset=UTF-8")
	public String listDvd(@PathVariable UUID dvdId) throws NotFoundException {
		DvdDTO dvd = dvdController.getDvdDTO(dvdId);
		return toJson(dvd);
	}
	
	// Returns a list of dvds of the film with id = filmId
	// Call from the web browser http://localhost:8080/dvd/film/${filmId}
	@GetMapping(value = "/film/{filmId}", produces = "application/json;charset=UTF-8")
	public String listDvdByFilmId(@PathVariable UUID filmId) throws NotFoundException {
		List<DvdDTO> dvds = dvdController.getDvdDTOByFilmId(filmId);
		return toJson(dvds);
	}
		
	// Updates the dvd with id = dvdId, and returns the updated object
	// Call from a Rest client http://localhost:8080/dvd/${dvdId}
	@PutMapping(value = "/{dvdId}", produces = "application/json;charset=UTF-8")
	public String UpdateDvd(@PathVariable UUID dvdId, @RequestBody String jDvd)
			throws NotFoundException, InvalidParamException {
		DvdDTO dvdUpdateData = new Gson().fromJson(jDvd, DvdDTO.class);
		DvdDTO updatedDvd = dvdController.updateDvd(dvdId, dvdUpdateData);
		return toJson(updatedDvd);
	}

	// Deletes the dvd with id = dvdId, and returns the deleted object
	// Call from a Rest client http://localhost:8080/dvd/${dvdId}
	@DeleteMapping(value = "/{dvdId}", produces = "application/json;charset=UTF-8")
	public String DeleteDvd(@PathVariable UUID dvdId) throws NotFoundException {
		DvdDTO deletedDvd = dvdController.deleteDvd(dvdId);
		return toJson(deletedDvd);
	}
	
	// Object to Json converter
	private String toJson(Object object) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
				.excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}
	
}
