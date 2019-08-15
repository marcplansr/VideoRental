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
import com.videorental.application.CustomerController;
import com.videorental.application.dto.CustomerDTO;
import com.videorental.utilities.InvalidParamException;
import com.videorental.utilities.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerRestController {

	@Autowired
	private CustomerController customerController;

	// Saves a new user in Database, and returns the saved object
	// Call from a Rest client http://localhost:8080/customer
	@PostMapping(produces = "application/json;charset=UTF-8")
	public String register(@RequestBody String jCustomer)
			throws InvalidParamException, NotFoundException {
		CustomerDTO newCustomer = new Gson().fromJson(jCustomer,
				CustomerDTO.class);
		CustomerDTO customer = customerController.register(newCustomer);
		return toJson(customer);
	}

	// Returns a list of all customers in Database
	// Call from the web browser http://localhost:8080/customer
	@GetMapping(produces = "application/json;charset=UTF-8")
	public String listCustomers() throws NotFoundException {
		List<CustomerDTO> customers = customerController.getAllCustomers();
		return toJson(customers);
	}

	// Returns the customer with id = customerId
	// Call from the web browser http://localhost:8080/customer/${customerId}
	@GetMapping(value = "/{customerId}", produces = "application/json;charset=UTF-8")
	public String getCustomer(@PathVariable UUID customerId)
			throws NotFoundException {
		CustomerDTO customer = customerController.getCustomerDTO(customerId);
		System.out.println(customer);
		return toJson(customer);
	}

	// Updates the customer with id = customerId, and returns the updated object
	// Call from the web browser http://localhost:8080/customer/${customerId}
	@PutMapping(value = "/{customerId}", produces = "application/json;charset=UTF-8")
	public String UpdateCustomer(@PathVariable UUID customerId,
			@RequestBody String jCustomer)
			throws NotFoundException, InvalidParamException {
		CustomerDTO customerUpdateData = new Gson().fromJson(jCustomer,
				CustomerDTO.class);

		CustomerDTO updatedCustomer = customerController
				.updateCustomer(customerId, customerUpdateData);
		return toJson(updatedCustomer);
	}

	// Deletes the customer with id = customerId, and returns the deleted object
	// Call from a Rest client http://localhost:8080/customer/${customerId}
	@DeleteMapping(value = "/{customerId}", produces = "application/json;charset=UTF-8")
	public String DeleteCustomer(@PathVariable UUID customerId)
			throws NotFoundException {
		CustomerDTO deletedCustomer = customerController
				.deleteCustomer(customerId);
		return toJson(deletedCustomer);
	}

	// Object to Json converter
	private String toJson(Object object) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		return gson.toJson(object);
	}

}
