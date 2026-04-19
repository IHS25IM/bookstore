package com.bookstore.bookstore.mapper;

import com.bookstore.bookstore.dto.CartLineDTO;
import org.springframework.stereotype.Component;
import com.bookstore.bookstore.entity.CartLine;

import java.util.List;

@Component
public class CartLineMapper {
    public CartLine toEntity(CartLineDTO dto){
        CartLine entity = new CartLine();
        entity.setCart(dto.getCart_id());
        return entity;
    }

    public CartLineDTO toResponseDTO(CartLine cartLine){
        CartLineDTO dto = new CartLineDTO();
        dto.setId(cartLine.getId());
        return dto;
    }

    public List<CartLineDTO> toResponseList (List<CartLine> cartLines){
        return cartLines.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
