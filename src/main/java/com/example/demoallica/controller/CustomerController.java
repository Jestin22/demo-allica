package com.example.demoallica.controller;


//Create a spring boot application.
//        Expose a HTTP method to post Customer details - First Name, Last Name and Date Of Birth
//        Use In memory database to persist the customer details.
//        Create another HTTP method to retrieve the customer details.


import com.example.demoallica.exceptionhandler.ResourceNotFoundException;
import com.example.demoallica.model.Customer;
import com.example.demoallica.response.CustomerResponse;
import com.example.demoallica.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody Customer customer) {
        CustomerResponse customerDetails = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDetails);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customerDetails = customerService.getCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customerDetails);
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("customer_id") Long customerId) throws ResourceNotFoundException {
        CustomerResponse customerDetails = customerService.getCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetails);
    }

}
