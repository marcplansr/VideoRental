package com.videorental.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.videorental.application.dto.RentalDTO;
import com.videorental.domain.Customer;
import com.videorental.domain.Dvd;
import com.videorental.domain.Rental;
import com.videorental.domain.enums.FilmType;
import com.videorental.persitence.IRentalRepository;
import com.videorental.utilities.DateHelpers;
import com.videorental.utilities.InvalidParamException;
import com.videorental.utilities.NotFoundException;

@Controller
public class RentalController {

	private static final Double PREMIUM_PRICE = 3.0;
	private static final Double BASE_PRICE = 1.0;

	@Autowired
	private IRentalRepository rentalRepository;
	@Autowired
	private DvdController dvdController;
	@Autowired
	private CustomerController customerController;

	public RentalDTO register(RentalDTO rentalDTO)
			throws InvalidParamException, NotFoundException {
		Dvd dvd = dvdController.getDvd(rentalDTO.getDvdId());
		Customer customer = customerController
				.getCustomer(rentalDTO.getCustomerId());
		Rental rental = new Rental(dvd, customer, DateHelpers.todayDate(),
				DateHelpers.datePlusDays(DateHelpers.todayDate(),
						rentalDTO.getNumberDays()),
				getInitialPayment(dvd, rentalDTO.getNumberDays()));

		dvdController.setDvdStatusRented(dvd);
		dvdController.save(dvd);
		customerController.AddBonusPoints(customer, getBonusPoints(dvd));
		customerController.save(customer);
		rental = save(rental);
		return new RentalDTO(rental);
	}

	public Rental save(Rental rental) throws InvalidParamException {
		if (rental == null)
			throw new InvalidParamException();
		try {
			return rentalRepository.save(rental);
		} catch (Exception e) {
			throw new InvalidParamException();
		}
	}

	public List<RentalDTO> getAllRentals() throws NotFoundException {
		Iterable<Rental> rentalList = rentalRepository
				.findAllByOrderByStartDateDesc();
		List<RentalDTO> rentalDTOList = new ArrayList<>();
		for (Rental rental : rentalList) {
			rentalDTOList.add(new RentalDTO(rental));
		}
		return rentalDTOList;
	}

	public List<RentalDTO> getOngoingRentals() throws NotFoundException {
		Iterable<Rental> rentalList = rentalRepository
				.findByReturnDateIsNullOrderByStartDateDesc();
		List<RentalDTO> rentalDTOList = new ArrayList<>();
		for (Rental rental : rentalList) {
			rentalDTOList.add(new RentalDTO(rental));
		}
		return rentalDTOList;
	}

	public RentalDTO getRentalById(UUID rentalId) throws NotFoundException {
		return new RentalDTO(rentalRepository.findById(rentalId)
				.orElseThrow(() -> new NotFoundException()));
	}

	protected Rental getRentalByDvd(UUID dvdId) throws NotFoundException {
		Dvd dvd = dvdController.getDvd(dvdId);
		return rentalRepository.findByDvdAndReturnDateIsNull(dvd);
	}

	public RentalDTO getRentalDTOByDvd(UUID dvdId) throws NotFoundException {
		RentalDTO rentalDTO = new RentalDTO(getRentalByDvd(dvdId));
		return rentalDTO;
	}

	public List<RentalDTO> getRentalHistoryByDvd(UUID dvdId)
			throws NotFoundException {
		Dvd dvd = dvdController.getDvd(dvdId);
		Iterable<Rental> rentals = rentalRepository
				.findByDvdOrderByStartDateDesc(dvd);
		List<RentalDTO> rentalDTOList = new ArrayList<>();
		for (Rental rental : rentals) {
			rentalDTOList.add(new RentalDTO(rental));
		}
		return rentalDTOList;
	}

	public List<RentalDTO> getRentalByCustomerId(UUID customerId)
			throws NotFoundException {
		Customer customer = customerController.getCustomer(customerId);
		Iterable<Rental> rentals = rentalRepository
				.findByCustomerAndReturnDateIsNullOrderByStartDateDesc(
						customer);
		List<RentalDTO> rentalDTOList = new ArrayList<>();
		for (Rental rental : rentals) {
			rentalDTOList.add(new RentalDTO(rental));
		}
		return rentalDTOList;
	}

	public RentalDTO terminateRental(UUID dvdId)
			throws NotFoundException, InvalidParamException {
		Rental rental = getRentalByDvd(dvdId);
		Dvd dvd = rental.getDvd();
		int extraDays = DateHelpers.daysBetween(rental.getDueDate(),
				DateHelpers.todayDate());
		if (extraDays > 0) {
			rental.setAdditionalPayment(getAdditionalPayment(dvd, extraDays));
		}
		dvdController.setDvdStatusAvailable(dvd);
		dvdController.save(dvd);
		rental.setReturnDate(new Date());
		rental = save(rental);
		return new RentalDTO(rental);
	}

	public RentalDTO updateRental(UUID rentalId, RentalDTO rentalUpdateData)
			throws NotFoundException, InvalidParamException {
		Rental rental = getRental(rentalId);
		if (rentalUpdateData.getDvdId() != null) {
			rental.setDvd(dvdController.getDvd(rentalUpdateData.getDvdId()));
		}
		if (rentalUpdateData.getCustomerId() != null) {
			rental.setCustomer(customerController
					.getCustomer(rentalUpdateData.getCustomerId()));
		}
		if (rentalUpdateData.getStartDate() != null) {
			rental.setStartDate(rentalUpdateData.getStartDate());
		}
		if (rentalUpdateData.getDueDate() != null) {
			rental.setDueDate(rentalUpdateData.getDueDate());
		}
		rental.setReturnDate(rentalUpdateData.getReturnDate());
		if (rentalUpdateData.getInitialPayment() != null) {
			rental.setInitialPayment(rentalUpdateData.getInitialPayment());
		}
		if (rentalUpdateData.getAdditionalPayment() != null) {
			rental.setAdditionalPayment(
					rentalUpdateData.getAdditionalPayment());
		}
		rental = save(rental);
		return new RentalDTO(rental);
	}

	public RentalDTO deleteRental(UUID rentalId) throws NotFoundException {
		RentalDTO rentalDTO = new RentalDTO(rentalRepository.findById(rentalId)
				.orElseThrow(() -> new NotFoundException()));
		rentalRepository.deleteById(rentalId);
		return rentalDTO;
	}

	private Rental getRental(UUID id) throws NotFoundException {
		return rentalRepository.findById(id)
				.orElseThrow(() -> new NotFoundException());
	}

	private Double getInitialPayment(Dvd dvd, Integer numberDays) {
		double output;
		int excessDays;
		switch (dvd.getFilm().getType()) {
		case NEW_RELEASE:
			output = numberDays * PREMIUM_PRICE;
			break;
		case REGULAR_FILM:
			excessDays = numberDays - 3;
			excessDays = (excessDays < 0) ? 0 : excessDays;
			output = PREMIUM_PRICE + excessDays * BASE_PRICE;
			break;
		default:
			excessDays = numberDays - 5;
			excessDays = (excessDays < 0) ? 0 : excessDays;
			output = BASE_PRICE * (1 + excessDays);
		}
		return output;
	}

	private Double getAdditionalPayment(Dvd dvd, int extraDays) {
		if (dvd.getFilm().getType() == FilmType.NEW_RELEASE) {
			return extraDays * PREMIUM_PRICE;
		}
		return extraDays * BASE_PRICE;
	}

	private Integer getBonusPoints(Dvd dvd) {
		if (dvd.getFilm().getType() == FilmType.NEW_RELEASE) {
			return 2;
		}
		return 1;
	}

}
