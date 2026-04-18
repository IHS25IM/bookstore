package mapper;

import dto.CartLineDTO;
import org.springframework.stereotype.Component;
import entity.CartLine;

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
