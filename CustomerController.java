//Customer Controller Class to perform save,update,delete and view Customer Details

package com.abc.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.model.Customer;
import com.abc.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	//Save Customer Details
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Customer customer){
		ResponseEntity<String> resp=null;
		try {
			Integer id=service.saveCustomer(customer);
			resp=new ResponseEntity<String>("Customer '" +id + "' created",HttpStatus.OK);
		}catch(Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	//Get all Customer Details
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		ResponseEntity<?> resp=null;
		List<Customer> list=service.getAllCustomers();
		//Check if the list empty or not
		if(list==null || list.isEmpty()) {
			String message="No Data Found";
			resp=new ResponseEntity<String>(message,HttpStatus.OK);
		}else {
			resp=new ResponseEntity<List<Customer>>(list,HttpStatus.OK); 
		}
		return resp;
	}
	
	//Delete Particular Customer Details
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id){
		ResponseEntity<String> resp=null;
		
		boolean present=service.isPresent(id);
		//to check if particular id which we entered is existed or not
		if(present) {
			service.deleteCustomer(id);
			resp=new ResponseEntity<String>("Deleted '" + id +"' successfully",HttpStatus.OK);
		}else {
			resp=new ResponseEntity<String>("'" + id + "' Not Exist",HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	//Update Customer Details
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Customer customer){
		ResponseEntity<String> resp=null;
		boolean present=service.isPresent(customer.getId());
		//to check if particular id which we entered is existed or not
		if(present) {
			service.updateCustomer(customer);
			resp=new ResponseEntity<String> ("Updated Successfully",HttpStatus.OK);
		}else {
			resp=new ResponseEntity<String>("Record '" +customer.getId() + " ' not found",HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
}
