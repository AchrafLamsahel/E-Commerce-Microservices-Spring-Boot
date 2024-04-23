package org.ordermicroservice.service;

import lombok.RequiredArgsConstructor;
import org.ordermicroservice.entities.Order;
import org.ordermicroservice.entities.OrderItem;
import org.ordermicroservice.repository.OrderRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final DecimalFormat decimalFormat;

    @Override
    public List<Order> findAll(Long userId, Pageable pageable) {
        return orderRepository.findAllByUserId(userId,pageable);
    }

    @Override
    public Order find(String id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public Order save(Order order) {
        order.setId(UUID.randomUUID().toString());
        order.setDate(new Date());
        String totalPriceFormat = decimalFormat.format(calculateTotalPrice(order.getItems()));
        order.setTotalPrice(Double.parseDouble(totalPriceFormat.replace(",",".")));
        return orderRepository.save(order);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }

    private double calculateTotalPrice(List<OrderItem> items){
        if (items == null) return 0;
        return items.stream()
                .mapToDouble(orderItem -> orderItem.getQty() * Double.parseDouble(orderItem.getPrice()))
                .sum();
    }
}
