package service;

import dto.CustomerDTO;
import entity.Customer;
import lombok.RequiredArgsConstructor;
import mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers(){
        return customerMapper.toResponseList(customerRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toResponseDTO(customer);
    }

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO request){
        Customer customer = customerMapper.toEntity(request);
        Customer saved = customerRepository.save(customer);
        return customerMapper.toResponseDTO(saved);
    }

    @Transactional
    public CustomerDTO updateCustomer(Long id, CustomerDTO request){
        Customer customer = customerMapper.toEntity(request);
        Customer saved = customerRepository.save(customer);
        return  customerMapper.toResponseDTO(saved);
    }

    @Transactional
    public void deleteCustomer(Long id){
        Customer customer = customerRepository.getReferenceById(id);
        customerRepository.deleteById(customer.getId());
    }
}
