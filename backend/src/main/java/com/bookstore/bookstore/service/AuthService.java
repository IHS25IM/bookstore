package com.bookstore.bookstore.service;

import com.bookstore.bookstore.dto.auth.AuthResponseDTO;
import com.bookstore.bookstore.dto.auth.LoginRequestDTO;
import com.bookstore.bookstore.dto.auth.RegisterRequestDTO;
import com.bookstore.bookstore.entity.Customer;
import com.bookstore.bookstore.mapper.CustomerMapper;
import com.bookstore.bookstore.repository.CustomerRepository;
import com.bookstore.bookstore.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomerMapper customerMapper;

    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setAddress(request.getAddress());
        customer.setZipCode(request.getZipCode());
        customer.setCity(request.getCity());
        customer.setCountry(request.getCountry());
        customerRepository.save(customer);

        String token = jwtService.generateToken(customer.getEmail());
        return new AuthResponseDTO(token, customer.getEmail(), customer.getFirstName(), customer.getLastName());
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        String token = jwtService.generateToken(customer.getEmail());
        return new AuthResponseDTO(token, customer.getEmail(), customer.getFirstName(), customer.getLastName());
    }
}
