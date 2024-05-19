package org.ordermicroservice.service;

import lombok.RequiredArgsConstructor;
import org.ordermicroservice.client.UserServiceClient;
import org.ordermicroservice.dtos.OrderResponseDTO;
import org.ordermicroservice.dtos.PageRequestDTO;
import org.ordermicroservice.dtos.UserDTO;
import org.ordermicroservice.entities.Order;
import org.ordermicroservice.entities.OrderItem;
import org.ordermicroservice.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final DecimalFormat decimalFormat;
    private final UserServiceClient userServiceClient;
    @Override
    public List<OrderResponseDTO> findAllUserOrders() {
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        orderRepository.findAll().forEach(order -> {
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
            orderResponseDTO.setOrder(order);
            UserDTO userDTO = userServiceClient.getUserByUserId(order.getUserId()).getBody();
            orderResponseDTO.setUser(userDTO);
            orderResponseDTOList.add(orderResponseDTO);
        });
        return orderResponseDTOList;
    }

    @Override
    public PageRequestDTO<Order> getAllOrdersPagination(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        Page<Order> orderPage = orderRepository.findAll(pageable);
        return new PageRequestDTO<>(
                orderPage.getContent(),
                orderPage.getNumber(),
                orderPage.getTotalPages(),
                (int) orderPage.getTotalElements()
        );
    }

    @Override
    public List<Order> findAllOrdersAuthenticated(Long userId, Pageable pageable) {
        return orderRepository.findAllByUserId(userId,pageable);
    }

    @Override
    public Order findOrderByOrderId(String id) {
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
