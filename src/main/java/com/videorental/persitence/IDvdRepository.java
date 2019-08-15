package com.videorental.persitence;

import java.util.UUID;

import com.videorental.domain.Dvd;

public interface IDvdRepository extends IGenericRepository<Dvd, UUID> {

	public Iterable<Dvd> findAllByOrderByFilmTitleAsc();

	public Iterable<Dvd> findByFilmId(UUID filmId);

}
