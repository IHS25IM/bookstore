package service;

import dto.OrderDTO;
import dto.OrderLineDTO;
import entity.Order;
import entity.OrderLine;
import lombok.RequiredArgsConstructor;
import mapper.OrderLineMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OrderLineRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    @Transactional(readOnly = true)
    public List<OrderLineDTO> getAllOrderLines() {
        return orderLineMapper.toResponseList(orderLineRepository.findAll());
    }

    @Transactional(readOnly = true)
    public OrderLineDTO getOrderLineById(Long id) {
        OrderLine orderLine = orderLineRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return orderLineMapper.toResponseDTO(orderLine);
    }

    @Transactional
    public OrderLineDTO createOrderLine(OrderLineDTO request) {
        OrderLine orderLine = orderLineMapper.toEntity(request);
        OrderLine saved = orderLineRepository.save(orderLine);
        return orderLineMapper.toResponseDTO(saved);
    }

    @Transactional
    public OrderLineDTO updateOrderLine(Long id, OrderLineDTO request) {
        OrderLine orderLine = orderLineMapper.toEntity(request);
        OrderLine saved = orderLineRepository.save(orderLine);
        return orderLineMapper.toResponseDTO(saved);
    }

    @Transactional
    public void deleteOrderLine(Long id) {
        OrderLine orderLine = orderLineRepository.getReferenceById(id);
        orderLineRepository.deleteById(orderLine.getId());
    }
}