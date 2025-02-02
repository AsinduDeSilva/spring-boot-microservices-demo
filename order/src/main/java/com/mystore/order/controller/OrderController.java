package com.mystore.order.controller;

import com.mystore.order.service.OrderService;
import com.mystore.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDTO> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrderById(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    public OrderDTO saveOrder(@RequestBody OrderDTO userDTO) {
        return orderService.saveOrder(userDTO);
    }

    @PutMapping
    public OrderDTO updateOrder(@RequestBody OrderDTO userDTO) {
        return orderService.updateOrder(userDTO);
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable Integer orderId) {
        return orderService.deleteOrder(orderId);
    }
}
