package org.ordermicroservice.service;

import org.ordermicroservice.entities.Order;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IOrderService {
    List<Order> findAll(Long userId, Pageable pageable);
    Order find(String id);
    Order save(Order order);
    long count();
}
