package com.bookstore.bookstore.dto;

import com.bookstore.bookstore.entity.Cart;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartLineDTO {
    private Long id;
    @NotNull
    private Cart cart_id;
}
