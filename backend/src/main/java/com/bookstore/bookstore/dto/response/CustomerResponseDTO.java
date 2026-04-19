package com.bookstore.bookstore.dto.response;

import com.bookstore.bookstore.dto.CartDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResponseDTO {
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    private String email;
    @NotNull
    private Integer zipCode;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private List<CartDTO> carts;
}
