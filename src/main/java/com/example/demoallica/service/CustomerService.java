package com.example.demoallica.service;

import com.example.demoallica.entities.CustomerEntity;
import com.example.demoallica.exceptionhandler.ResourceNotFoundException;
import com.example.demoallica.mapper.CustomerMapper;
import com.example.demoallica.model.Customer;
import com.example.demoallica.repository.CustomerRepository;
import com.example.demoallica.response.CustomerResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponse saveCustomer(Customer customer) {
        CustomerEntity entity = INSTANCE.toEntity(customer);
        CustomerEntity savedCustomer = customerRepository.save(entity);
        return INSTANCE.toResponse(savedCustomer);
    }

    public CustomerResponse getCustomer(Long customerId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            return INSTANCE.toResponse(customerOptional.get());
        } else throw new ResourceNotFoundException("Customer Not Found");
    }

    public List<CustomerResponse> getCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        return INSTANCE.toResponseList(customers);

    }
}
