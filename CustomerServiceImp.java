//Customer Service Implementation Class which implements Customer Service Interface
//Define all the unimplemented methods here

package com.abc.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abc.model.Customer;
import com.abc.repo.CustomerRepository;
import com.abc.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService {
	@Autowired
	private CustomerRepository repo;
	
	@Transactional
	public Integer saveCustomer(Customer c) {
		return repo.save(c).getId();
	}

	@Transactional
	public void updateCustomer(Customer c) {
		repo.save(c);
	}

	@Transactional
	public void deleteCustomer(Integer id) {
		repo.deleteById(id);
	}

	@Transactional(readOnly=true)
	public Optional<Customer> getOneCustomer(Integer id) {
		return repo.findById(id);
	}

	@Transactional(readOnly=true)
	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}

	@Transactional(readOnly=true)
	public boolean isPresent(Integer id) {
		return repo.existsById(id);
	}

}
