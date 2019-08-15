package com.videorental.persitence;

import java.util.UUID;

import com.videorental.domain.Film;

public interface IFilmRepository extends IGenericRepository<Film, UUID> {

	public Iterable<Film> findAllByOrderByTitleAsc();

	public Film findByTitle(String title);

	public Iterable<Film> findByTitleContainingIgnoreCase(String keyword);

}
