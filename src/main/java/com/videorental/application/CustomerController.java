package com.videorental.application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.videorental.application.dto.CustomerDTO;
import com.videorental.domain.Customer;
import com.videorental.persitence.ICustomerRepository;
import com.videorental.utilities.InvalidParamException;
import com.videorental.utilities.NotFoundException;

@Controller
public class CustomerController {

	@Autowired
	private ICustomerRepository customerRepository;

	public CustomerDTO register(CustomerDTO customerDTO)
			throws InvalidParamException, NotFoundException {
		Customer customer = new Customer(customerDTO.getName(),
				customerDTO.getSurnames(), customerDTO.getEmail(),
				customerDTO.getPhone(), customerDTO.getBonusPoints());
		customer = save(customer);
		return new CustomerDTO(customer);
	}

	public Customer save(Customer customer) throws InvalidParamException {
		if (customer == null)
			throw new InvalidParamException();
		try {
			return customerRepository.save(customer);
		} catch (Exception e) {
			throw new InvalidParamException();
		}
	}

	public List<CustomerDTO> getAllCustomers() throws NotFoundException {
		Iterable<Customer> customerList = customerRepository.findAllByOrderBySurnamesAsc();
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		for (Customer customer : customerList) {
			customerDTOList.add(new CustomerDTO(customer));
		}
		return customerDTOList;
	}

	public Customer getCustomer(UUID id) throws NotFoundException {
		return customerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException());
	}

	public CustomerDTO getCustomerDTO(UUID id) throws NotFoundException {
		return new CustomerDTO(getCustomer(id));
	}

	public CustomerDTO updateCustomer(UUID customerId,
			CustomerDTO customerUpdateData)
			throws NotFoundException, InvalidParamException {
		Customer customer = getCustomer(customerId);
		if (customerUpdateData.getName() != null) {
			customer.setName(customerUpdateData.getName());
		}
		if (customerUpdateData.getSurnames() != null) {
			customer.setSurnames(customerUpdateData.getSurnames());
		}
		if (customerUpdateData.getEmail() != null) {
			customer.setEmail(customerUpdateData.getEmail());
		}
		if (customerUpdateData.getPhone() != null) {
			customer.setPhone(customerUpdateData.getPhone());
		}
		if (customerUpdateData.getBonusPoints() != null) {
			customer.setBonusPoints(customerUpdateData.getBonusPoints());
		} 
		customer = save(customer);
		return new CustomerDTO(customer);
	}

	public CustomerDTO deleteCustomer(UUID customerId)
			throws NotFoundException {
		CustomerDTO customerDTO = new CustomerDTO(
				customerRepository.findById(customerId)
						.orElseThrow(() -> new NotFoundException()));
		customerRepository.deleteById(customerId);
		return customerDTO;
	}

	public void AddBonusPoints(Customer customer, Integer bonusPoints) {
		customer.setBonusPoints(customer.getBonusPoints() + bonusPoints);
	}

}
