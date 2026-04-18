package dto;


import entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Long id;
    @NotNull
    private Long customer_id;
    @NotNull
    private Long book_id;
    @NotNull
    private Integer quantity;
    @NotNull
    private LocalDateTime orderDate;
    @NotNull
    private OrderStatus status;
    @NotNull
    private List<OrderLineDTO> orderLine;
}
