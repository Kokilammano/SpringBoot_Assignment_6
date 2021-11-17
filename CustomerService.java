//Customer Service Interface 

package com.abc.service;

import java.util.List;
import java.util.Optional;

import com.abc.model.Customer;

public interface CustomerService {
	public Integer saveCustomer(Customer c);
	public void updateCustomer(Customer c);
	public void deleteCustomer(Integer id);
	public Optional<Customer> getOneCustomer(Integer id);
	public List<Customer> getAllCustomers();
	public boolean isPresent(Integer id);
}
