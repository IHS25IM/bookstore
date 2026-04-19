package com.bookstore.bookstore.mapper;

import com.bookstore.bookstore.dto.CustomerDTO;
import com.bookstore.bookstore.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {
    @Autowired
    private CartMapper cartMapper;

    public Customer toEntity(CustomerDTO dto){
        Customer entity = new Customer();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAdress(dto.getAdress());
        entity.setZipCode(dto.getZipCode());
        entity.setCity(dto.getCity());
        entity.setCountry(dto.getCountry());
        entity.setCarts(dto.getCarts().stream()
                .map(cartMapper::toEntity)
                .toList());
        return entity;
    }

    public CustomerDTO toResponseDTO(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setAdress(customer.getAdress());
        dto.setZipCode(customer.getZipCode());
        dto.setCity(customer.getCity());
        dto.setCountry(customer.getCountry());
        dto.setCarts(customer.getCarts().stream()
                .map(cartMapper::toReponseDTO)
                .toList());
        return dto;
    }

    public List<CustomerDTO> toResponseList(List<Customer> customers){
        return customers.stream()
                .map(this::toResponseDTO)
                .toList();
    }

}
