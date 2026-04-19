package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    Optional<OrderLine> findByOrderId(Long orderId);
}
