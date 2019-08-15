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
import com.videorental.application.RentalController;
import com.videorental.application.dto.RentalDTO;
import com.videorental.utilities.InvalidParamException;
import com.videorental.utilities.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/rental")
public class RentalRestController {

	@Autowired
	private RentalController rentalController;
	
	// Saves a new rental in Database, and returns the saved object
	// Call from a Rest client http://localhost:8080/rental
	@PostMapping(produces = "application/json;charset=UTF-8")
	public String register(@RequestBody String jRental)
			throws InvalidParamException, NotFoundException {
		RentalDTO newRental = new Gson().fromJson(jRental, RentalDTO.class);
		RentalDTO rental = rentalController.register(newRental);
		return toJson(rental);
	}

	// Returns a list of all ongoing rentals in Database
	// Call from the web browser http://localhost:8080/rental
	@GetMapping(produces = "application/json;charset=UTF-8")
	public String listOngoingRentals() throws NotFoundException {
		List<RentalDTO> rentals = rentalController.getOngoingRentals();
		return toJson(rentals);
	}

	// Returns a list of all rentals in Database, ongoing and historical
	// Call from the web browser http://localhost:8080/rental/history
	@GetMapping(value = "/history", produces = "application/json;charset=UTF-8")
	public String listAllRentals() throws NotFoundException {
		List<RentalDTO> rentals = rentalController.getAllRentals();
		return toJson(rentals);
	}
	
	// Returns rental with rental id = rentalId
	// Call from the web browser http://localhost:8080/rental/${rentalId}
	@GetMapping(value = "/{rentalId}", produces = "application/json;charset=UTF-8")
	public String listRentalById(@PathVariable UUID rentalId) throws NotFoundException {
		RentalDTO rental = rentalController.getRentalById(rentalId);
		return toJson(rental);
	}

	// Returns the ongoing rental with dvd id = dvdId
	// Call from the web browser http://localhost:8080/rental/dvd/${dvdId}
	@GetMapping(value = "/dvd/{dvdId}", produces = "application/json;charset=UTF-8")
	public String listRentalByDvd(@PathVariable UUID dvdId) throws NotFoundException {
		RentalDTO rental = rentalController.getRentalDTOByDvd(dvdId);
		return toJson(rental);
	}

	// Returns all rentals in Database with dvd id = dvdId, ongoing and historical
	// Call from the web browser http://localhost:8080/rental/history/${dvdId}
	@GetMapping(value = "/history/{dvdId}", produces = "application/json;charset=UTF-8")
	public String listHistoryByDvd(@PathVariable UUID dvdId)
			throws NotFoundException {
		List<RentalDTO> rentals = rentalController
				.getRentalHistoryByDvd(dvdId);
		return toJson(rentals);
	}
	
	// Returns all active rentals in Database of customer with id = customerId
	// Call from the web browser http://localhost:8080/rental/customer/${dvdId}
	@GetMapping(value = "/customer/{customerId}", produces = "application/json;charset=UTF-8")
	public String listHistoryByCustomerId(@PathVariable UUID customerId)
			throws NotFoundException {
		List<RentalDTO> rentals = rentalController
				.getRentalByCustomerId(customerId);
		return toJson(rentals);
	}

	// Terminates and updates rental with dvd id = dvdId, and returns the updated object
	// Call from a Rest client http://localhost:8080/rental/terminate/${dvdId}
	@PutMapping(value = "/terminate/{dvdId}", produces = "application/json;charset=UTF-8")
	public String TerminateRental(@PathVariable UUID dvdId)
			throws NotFoundException, InvalidParamException {
		RentalDTO returnedRental = rentalController.terminateRental(dvdId);
		return toJson(returnedRental);
	}

	// Updates the rental with id = rentalId, and returns the updated object
	// Call from the web browser http://localhost:8080/rental/${rentalId}
	@PutMapping(value = "/{rentalId}", produces = "application/json;charset=UTF-8")
	public String UpdateRental(@PathVariable UUID rentalId,
			@RequestBody String jRental)
			throws NotFoundException, InvalidParamException {
		RentalDTO rentalUpdateData = new Gson().fromJson(jRental,
				RentalDTO.class);
		RentalDTO updatedRental = rentalController.updateRental(rentalId,
				rentalUpdateData);
		return toJson(updatedRental);
	}

	// Deletes the rental with id = rentalId, and returns the deleted object
	// Call from a Rest client http://localhost:8080/rental/${rentalId}
	@DeleteMapping(value = "/{rentalId}", produces = "application/json;charset=UTF-8")
	public String DeleteRental(@PathVariable UUID rentalId)
			throws NotFoundException {
		RentalDTO deletedRental = rentalController.deleteRental(rentalId);
		return toJson(deletedRental);
	}

	// Object to Json converter
	private String toJson(Object object) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
				.excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}

}
