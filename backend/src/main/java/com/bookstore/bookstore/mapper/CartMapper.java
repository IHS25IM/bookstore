package com.bookstore.bookstore.mapper;

import com.bookstore.bookstore.dto.CartDTO;
import com.bookstore.bookstore.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    @Autowired
    private CartLineMapper cartLineMapper;

    public Cart toEntity(CartDTO dto){
        Cart entity = new Cart();
        entity.setCustomer(dto.getCustomer_id());
        entity.setCartLines(dto.getCartLines().stream()
                .map(cartLineMapper::toEntity)
                .toList());
        return entity;
    }

    public CartDTO toReponseDTO(Cart cart){
        CartDTO dto = new CartDTO();
        dto.setCustomer_id(cart.getCustomer());
        dto.setCartLines(cart.getCartLines().stream()
                .map(cartLineMapper::toResponseDTO)
                .toList());
        return dto;
    }

    public List<CartDTO> toReponseList(List<Cart> carts){
        return carts.stream()
                .map(this::toReponseDTO)
                .toList();
    }
}
