package com.bookstore.bookstore.service;

import com.bookstore.bookstore.dto.CartDTO;
import com.bookstore.bookstore.entity.Cart;
import lombok.RequiredArgsConstructor;
import com.bookstore.bookstore.mapper.CartMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.bookstore.repository.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Transactional(readOnly = true)
    public List<CartDTO> getAllCarts(){
        return cartMapper.toReponseList(cartRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CartDTO getCartById(Long id){
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cartMapper.toReponseDTO(cart);
    }

    @Transactional
    public CartDTO createCart(CartDTO request){
        Cart cart = cartMapper.toEntity(request);
        Cart saved = cartRepository.save(cart);
        return cartMapper.toReponseDTO(saved);
    }

    @Transactional
    public CartDTO updateCart(Long id, CartDTO request){
        Cart cart = cartMapper.toEntity(request);
        Cart saved = cartRepository.save(cart);
        return  cartMapper.toReponseDTO(saved);
    }

    @Transactional
    public void deleteCart(Long id){
        Cart cart = cartRepository.getReferenceById(id);
        cartRepository.deleteById(cart.getId());
    }
}
