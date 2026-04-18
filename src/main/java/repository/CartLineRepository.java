package repository;

import entity.CartLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartLineRepository extends JpaRepository<CartLine, Long> {
    Optional<CartLine> findByCartId(Long cartId);
}
