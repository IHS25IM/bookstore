package mapper;

import dto.CustomerDTO;
import entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {
    @Autowired
    private CartLineMapper cartLineMapper;

    public Customer toEntity(CustomerDTO dto){
        Customer entity = new Customer();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAdress(dto.getAdress());
        entity.setZipCode(dto.getZipCode());
        entity.setCity(dto.getCity());
        entity.setCountry(dto.getCountry());
        entity.setCartLines(dto.getCartLines().stream()
                .map(cartLineMapper::toEntity)
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
        dto.setCartLines(customer.getCartLines().stream()
                .map(cartLineMapper::toResponseDTO)
                .toList());
        return dto;
    }

    public List<CustomerDTO> toResponseList(List<Customer> customers){
        return customers.stream()
                .map(this::toResponseDTO)
                .toList();
    }

}
