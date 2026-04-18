package service;

import dto.BookDTO;
import dto.OrderDTO;
import entity.Book;
import entity.Order;
import lombok.RequiredArgsConstructor;
import mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrders(){
        return orderMapper.toReponseList(orderRepository.findAll());
    }

    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.toReponseDTO(order);
    }

    @Transactional
    public OrderDTO createOrder(OrderDTO request){
        Order order = orderMapper.toEntity(request);
        Order saved = orderRepository.save(order);
        return orderMapper.toReponseDTO(saved);
    }

    @Transactional
    public OrderDTO updateOrder(Long id, OrderDTO request){
        Order order = orderMapper.toEntity(request);
        Order saved = orderRepository.save(order);
        return  orderMapper.toReponseDTO(saved);
    }

    @Transactional
    public void deleteOrder(Long id){
        Order order = orderRepository.getReferenceById(id);
        orderRepository.deleteById(order.getOrder_id());
    }
}
