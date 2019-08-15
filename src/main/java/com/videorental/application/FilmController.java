package com.videorental.application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.videorental.application.dto.FilmDTO;
import com.videorental.domain.Film;
import com.videorental.persitence.IFilmRepository;
import com.videorental.utilities.InvalidParamException;
import com.videorental.utilities.NotFoundException;

@Controller
public class FilmController {

	@Autowired
	private IFilmRepository filmRepository;

	public FilmDTO register(FilmDTO filmDTO)
			throws InvalidParamException, NotFoundException {
		Film film = new Film(filmDTO.getTitle(), filmDTO.getYear(),
				filmDTO.getDirector(), filmDTO.getType());
		film = save(film);
		return new FilmDTO(film);
	}

	public Film save(Film film) throws InvalidParamException {
		if (film == null)
			throw new InvalidParamException();
		try {
			return filmRepository.save(film);
		} catch (Exception e) {
			throw new InvalidParamException();
		}
	}

	public List<FilmDTO> getAllFilms() throws NotFoundException {
		Iterable<Film> filmList = filmRepository.findAllByOrderByTitleAsc();
		List<FilmDTO> filmDTOList = new ArrayList<>();
		for (Film film : filmList) {
			filmDTOList.add(new FilmDTO(film));
		}
		return filmDTOList;
	}

	public Film getFilm(UUID id) throws NotFoundException {
		return filmRepository.findById(id)
				.orElseThrow(() -> new NotFoundException());
	}

	public FilmDTO getFilmDTO(UUID id) throws NotFoundException {
		return new FilmDTO(getFilm(id));
	}
	
	public FilmDTO getFilmByTitle(String title) throws NotFoundException {
		return new FilmDTO(filmRepository.findByTitle(title));
	}

	public List<FilmDTO> getByKeyword(String keyword) throws NotFoundException {
		Iterable<Film> filmList = filmRepository
				.findByTitleContainingIgnoreCase(keyword);
		List<FilmDTO> filmDTOList = new ArrayList<>();
		for (Film film : filmList) {
			filmDTOList.add(new FilmDTO(film));
		}
		return filmDTOList;
	}
	
	public FilmDTO updateFilm(UUID filmId,
			FilmDTO filmUpdateData)
			throws NotFoundException, InvalidParamException {
		Film film = getFilm(filmId);

		if (filmUpdateData.getTitle() != null) {
			film.setTitle(filmUpdateData.getTitle());
		}
		if (filmUpdateData.getYear() != null) {
			film.setYear(filmUpdateData.getYear());
		}
		if (filmUpdateData.getDirector() != null) {
			film.setDirector(filmUpdateData.getDirector());
		}
		if (filmUpdateData.getType() != null) {
			film.setType(filmUpdateData.getType());
		}
		film = save(film);
		return new FilmDTO(film);
	}

	public FilmDTO deleteFilm(UUID filmId)
			throws NotFoundException {
		FilmDTO filmDTO = new FilmDTO(
				filmRepository.findById(filmId)
						.orElseThrow(() -> new NotFoundException()));
		filmRepository.deleteById(filmId);
		return filmDTO;
	}

}
