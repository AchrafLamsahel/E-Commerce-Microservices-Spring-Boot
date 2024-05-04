package org.ordermicroservice.service;

import org.ordermicroservice.dtos.OrderResponseDTO;
import org.ordermicroservice.entities.Order;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IOrderService {
    List<OrderResponseDTO> findAllUserOrders();
    List<Order> findAllOrdersAuthenticated(Long userId, Pageable pageable);
    Order findOrderByOrderId(String id);
    Order save(Order order);
    long count();
}
