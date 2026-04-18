package mapper;

import dto.OrderLineDTO;
import entity.OrderLine;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderLineMapper {
    public OrderLine toEntity(OrderLineDTO dto){
        OrderLine entity = new OrderLine();
        entity.setOrder(dto.getOrder_id());
        return entity;
    }

    public OrderLineDTO toResponseDTO(OrderLine orderLine){
        OrderLineDTO dto = new OrderLineDTO();
        dto.setOrder_id(orderLine.getOrder());
        return dto;
    }

    public List<OrderLineDTO> toResponseList(List<OrderLine> orderLines){
        return orderLines.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
