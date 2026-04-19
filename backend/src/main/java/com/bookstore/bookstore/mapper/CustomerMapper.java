package com.bookstore.bookstore.mapper;

import com.bookstore.bookstore.dto.request.CustomerRequestDTO;
import com.bookstore.bookstore.dto.response.CustomerResponseDTO;
import com.bookstore.bookstore.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {
    @Autowired
    private CartMapper cartMapper;

    public Customer toEntity(CustomerRequestDTO dto){
        Customer entity = new Customer();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setZipCode(dto.getZipCode());
        entity.setCity(dto.getCity());
        entity.setCountry(dto.getCountry());
        entity.setCarts(dto.getCarts().stream()
                .map(cartMapper::toEntity)
                .toList());
        return entity;
    }

    public CustomerResponseDTO toResponseDTO(Customer customer){
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setAddress(customer.getAddress());
        dto.setZipCode(customer.getZipCode());
        dto.setCity(customer.getCity());
        dto.setCountry(customer.getCountry());
        dto.setCarts(customer.getCarts().stream()
                .map(cartMapper::toReponseDTO)
                .toList());
        return dto;
    }

    public List<CustomerResponseDTO> toResponseList(List<Customer> customers){
        return customers.stream()
                .map(this::toResponseDTO)
                .toList();
    }

}
