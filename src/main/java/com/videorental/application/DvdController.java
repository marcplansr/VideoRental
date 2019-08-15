package com.videorental.application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.videorental.application.dto.DvdDTO;
import com.videorental.domain.Dvd;
import com.videorental.domain.Rental;
import com.videorental.domain.enums.DvdStatus;
import com.videorental.persitence.IDvdRepository;
import com.videorental.utilities.InvalidParamException;
import com.videorental.utilities.NotFoundException;

@Controller
public class DvdController {

	@Autowired
	private IDvdRepository dvdRepository;

	@Autowired
	private RentalController rentalController;
	@Autowired
	private FilmController filmController;

	public DvdDTO register(DvdDTO dvdDTO)
			throws InvalidParamException, NotFoundException {
		Dvd dvd = new Dvd(filmController.getFilm(dvdDTO.getFilmId()));
		dvd = save(dvd);
		return new DvdDTO(dvd);
	}

	public Dvd save(Dvd dvd) throws InvalidParamException {
		if (dvd == null)
			throw new InvalidParamException();
		try {
			return dvdRepository.save(dvd);
		} catch (Exception e) {
			throw new InvalidParamException();
		}
	}

	public List<DvdDTO> getAllDvds() throws NotFoundException {
		Iterable<Dvd> dvdList = dvdRepository.findAllByOrderByFilmTitleAsc();
		List<DvdDTO> dvdDTOList = new ArrayList<>();
		for (Dvd dvd : dvdList) {
			DvdDTO dvdDTO = updateDueDate(dvd);
			dvdDTOList.add(dvdDTO);
		}
		return dvdDTOList;
	}

	public Dvd getDvd(UUID id) throws NotFoundException {
		return dvdRepository.findById(id)
				.orElseThrow(() -> new NotFoundException());
	}

	public DvdDTO getDvdDTO(UUID id) throws NotFoundException {
		DvdDTO dvdDTO = updateDueDate(getDvd(id));
		return dvdDTO;
	}
	
	public Iterable<Dvd> getDvdByFilmId(UUID filmId) throws NotFoundException {
		return dvdRepository.findByFilmId(filmId);
	}
	
	public List<DvdDTO> getDvdDTOByFilmId(UUID filmId)
			throws NotFoundException {
		Iterable<Dvd> dvdList = getDvdByFilmId(filmId);
		List<DvdDTO> dvdDTOList = new ArrayList<>();
		for (Dvd dvd : dvdList) {
			DvdDTO dvdDTO = updateDueDate(dvd);
			dvdDTOList.add(dvdDTO);
		}
		return dvdDTOList;
	}

	public DvdDTO updateDvd(UUID dvdId, DvdDTO dvdUpdateData)
			throws NotFoundException, InvalidParamException {
		Dvd dvd = getDvd(dvdId);
		if (dvdUpdateData.getFilmId() != null) {
			dvd.setFilm(filmController.getFilm(dvdUpdateData.getFilmId()));
		}
		if (dvdUpdateData.getStatus() != null) {
			dvd.setStatus(dvdUpdateData.getStatus());
		}
		Dvd updatedDvd = save(dvd);
		return new DvdDTO(updatedDvd);
	}

	public DvdDTO deleteDvd(UUID dvdId) throws NotFoundException {
		DvdDTO dvdDTO = new DvdDTO(dvdRepository.findById(dvdId)
				.orElseThrow(() -> new NotFoundException()));
		dvdRepository.deleteById(dvdId);
		return dvdDTO;
	}

	public void setDvdStatusAvailable(Dvd dvd) {
		dvd.setStatus(DvdStatus.AVAILABLE);
	}

	public void setDvdStatusRented(Dvd dvd) {
		dvd.setStatus(DvdStatus.RENTED);
	}

	private DvdDTO updateDueDate(Dvd dvd) throws NotFoundException {
		DvdDTO dvdDTO = new DvdDTO(dvd);
		if (dvd.getStatus() == DvdStatus.RENTED) {
			Rental rental = rentalController.getRentalByDvd(dvd.getId());
			dvdDTO.setDueDate(rental.getDueDate());
		} else {
			dvdDTO.setDueDate(null);
		}
		return dvdDTO;
	}
	
}
