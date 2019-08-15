package com.videorental.persitence;

import java.util.UUID;

import com.videorental.domain.Customer;

public interface ICustomerRepository
		extends IGenericRepository<Customer, UUID> {

	public Iterable<Customer> findAllByOrderBySurnamesAsc();

}
