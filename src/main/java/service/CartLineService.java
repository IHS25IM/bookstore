package service;

import dto.CartLineDTO;
import entity.CartLine;
import lombok.RequiredArgsConstructor;
import mapper.CartLineMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CartLineRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CartLineService {
    private final CartLineRepository cartLineRepository;
    private final CartLineMapper cartLineMapper;

    @Transactional(readOnly = true)
    public List<CartLineDTO> getAllCartLines() {
        return cartLineMapper.toResponseList(cartLineRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CartLineDTO getCartLineById(Long id) {
        CartLine cartLine = cartLineRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart line not found"));
        return cartLineMapper.toResponseDTO(cartLine);
    }

    @Transactional
    public CartLineDTO createCartLine(CartLineDTO request) {
        CartLine cartLine = cartLineMapper.toEntity(request);
        CartLine saved = cartLineRepository.save(cartLine);
        return cartLineMapper.toResponseDTO(saved);
    }

    @Transactional
    public CartLineDTO updateCartLine(Long id, CartLineDTO request) {
        CartLine cartLine = cartLineMapper.toEntity(request);
        CartLine saved = cartLineRepository.save(cartLine);
        return cartLineMapper.toResponseDTO(saved);
    }

    @Transactional
    public void deleteCartLine(Long id) {
        CartLine cartLine = cartLineRepository.getReferenceById(id);
        cartLineRepository.deleteById(cartLine.getId());
    }
}
