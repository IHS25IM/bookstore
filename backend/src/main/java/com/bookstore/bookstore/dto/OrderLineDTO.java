package com.bookstore.bookstore.dto;

import com.bookstore.bookstore.entity.Order;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderLineDTO {
    private Long id;
    @NotNull
    private Order order_id;
}
