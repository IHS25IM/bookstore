package mapper;

import dto.OrderDTO;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    @Autowired
    private OrderLineMapper orderLineMapper;

    public Order toEntity(OrderDTO dto){
        Order order = new Order();
        order.setOrder_id(dto.getId());
        order.setCustomer_id(dto.getCustomer_id());
        order.setBook_id(dto.getBook_id());
        order.setQuantity(dto.getQuantity());
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
        order.setOrderLines(dto.getOrderLine().stream()
                .map(orderLineMapper::toEntity)
                .toList());
        return order;
    }

    public OrderDTO toReponseDTO(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getOrder_id());
        dto.setCustomer_id(order.getCustomer_id());
        dto.setBook_id(order.getBook_id());
        dto.setQuantity(order.getQuantity());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setOrderLine(order.getOrderLines().stream()
                .map(orderLineMapper::toResponseDTO)
                .toList());
        return dto;
    }

    public List<OrderDTO> toReponseList(List<Order> orders){
        return orders.stream()
                .map(this::toReponseDTO)
                .toList();
    }

}
