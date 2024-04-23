package org.ordermicroservice.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.ordermicroservice.app.OrderApp;
import org.ordermicroservice.dtos.PageRequestDTO;
import org.ordermicroservice.entities.Address;
import org.ordermicroservice.entities.Order;
import org.ordermicroservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;
import java.util.Collections;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderApp orderApp;
    private final OrderService orderService;

    @GetMapping
    private PageRequestDTO<Order> handleGetOrders(HttpServletRequest request){
        String email = request.getUserPrincipal().getName();
        String page = request.getParameter("page");
        if( !email.isEmpty() && !page.isEmpty())
            return  orderApp.getAll(email,request.getParameter("page"));
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


}
