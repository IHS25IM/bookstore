package controller;

import dto.BookDTO;
import dto.CartDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts(){
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id){
        return ResponseEntity.ok(cartService.getCartById(id));
    }

    @PostMapping
    public ResponseEntity<CartDTO> createCart(@Valid @RequestBody CartDTO request){
        CartDTO created = cartService.createCart(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @Valid @RequestBody CartDTO request){
        return ResponseEntity.ok(cartService.updateCart(id, request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
