package com.videorental.persitence;

import java.util.UUID;

import com.videorental.domain.Customer;
import com.videorental.domain.Dvd;
import com.videorental.domain.Rental;

public interface IRentalRepository extends IGenericRepository<Rental, UUID> {

	public Iterable<Rental> findAllByOrderByStartDateDesc();

	public Iterable<Rental> findByReturnDateIsNullOrderByStartDateDesc();

	public Rental findByDvdAndReturnDateIsNull(Dvd dvd);

	public Iterable<Rental> findByDvdOrderByStartDateDesc(Dvd dvd);

	public Iterable<Rental> findByCustomerAndReturnDateIsNullOrderByStartDateDesc(
			Customer customer);

}
