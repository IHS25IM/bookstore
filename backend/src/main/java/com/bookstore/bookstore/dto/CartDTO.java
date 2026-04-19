package com.bookstore.bookstore.dto;

import com.bookstore.bookstore.entity.Customer;
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
public class CartDTO {
    private Long id;
    @NotNull
    private List<CartLineDTO> cartLines;
    @NotNull
    private Customer customer_id;
}
