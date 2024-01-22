package com.example.demoallica.mapper;

import com.example.demoallica.entities.CustomerEntity;
import com.example.demoallica.model.Customer;
import com.example.demoallica.response.CustomerResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface CustomerMapper {

    CustomerEntity toEntity(Customer customer);

    CustomerResponse toResponse(CustomerEntity customer);

    List<CustomerResponse> toResponseList(List<CustomerEntity> customer);
}
