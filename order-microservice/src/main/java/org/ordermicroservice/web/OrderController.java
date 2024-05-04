package org.ordermicroservice.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.ordermicroservice.app.OrderApp;
import org.ordermicroservice.client.UserServiceClient;
import org.ordermicroservice.dtos.OrderResponseDTO;
import org.ordermicroservice.dtos.PageRequestDTO;
import org.ordermicroservice.dtos.UserDTO;
import org.ordermicroservice.entities.Address;
import org.ordermicroservice.entities.Order;
import org.ordermicroservice.repository.OrderRepository;
import org.ordermicroservice.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderApp orderApp;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;

    @GetMapping("/allOrders")
    private List<OrderResponseDTO> handleGetOrdersUsers() {
        return orderService.findAllUserOrders();
    }

    @GetMapping
    private PageRequestDTO<Order> handleGetOrders(HttpServletRequest request) {
        String email = request.getUserPrincipal().getName();
        String page = request.getParameter("page");
        if (!email.isEmpty() && !page.isEmpty())
            return orderApp.getAll(email, request.getParameter("page"));
        return null;
    }

    @PostMapping("/save")
    public ResponseEntity<?> handleSave(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @PostMapping("/delivery-address")
    public String handleDeliveryAddress(@RequestBody Address address, HttpSession session) {
        Order order = (Order) session.getAttribute("cart");
        order.setDeliveryAddress(address);
        return "";
    }

    @GetMapping("/all")
    public PageRequestDTO<Order> getAll(@RequestParam String email, @RequestParam String page) {
        int pageToInt = calculatePage(page);
        long count = orderService.count();
        int totalPages = (int) ((count + 9) / 10);
        UserDTO user = userServiceClient.getUserByEmail(email).getBody();
        assert user != null;
        List<Order> orders = orderRepository.findAllByUserId(user.getUserId(), PageRequest.of(pageToInt, 10, Sort.by(Sort.Direction.DESC, "date")));
        return new PageRequestDTO<>(orders, pageToInt, orders.isEmpty() ? 1 : totalPages, (int) count);
    }

    private int calculatePage(String page) {
        try {
            return Integer.parseInt(page);
        } catch (NumberFormatException e) {
            return 0;
        }
    }


}
