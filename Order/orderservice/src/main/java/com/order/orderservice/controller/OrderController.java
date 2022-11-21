package com.order.orderservice.controller;

import com.order.orderservice.model.entity.Order;
import com.order.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrder() {
        return ResponseEntity.ok().body(orderService.readOrder());
    }

    @PostMapping("/order")
    public ResponseEntity<String> saveOrder(@RequestBody Order order) {
        var process = orderService.createOrder(order);
        if(process) {
            return ResponseEntity.ok().body("OrderController successfully saved!");
        }

        return ResponseEntity.badRequest().body("OrderController failed to save!");
    }

    @PutMapping("/update-order")
    public ResponseEntity<String> updateOrder(@RequestParam int id, @RequestBody Order order) {
        if(orderService.updateOrder(id, order)) {
            return ResponseEntity.ok().body("OrderController has been updated!");
        }

        return ResponseEntity.badRequest().body("OrderController not found or error!");
    }

    @DeleteMapping("/delete-order")
    public ResponseEntity<String> deleteOrder(@RequestParam int id) {
        if(orderService.deleteOrder(id)) {
            return ResponseEntity.ok().body("OrderController successfully deleted!");
        }

        return ResponseEntity.badRequest().body("OrderController not found or error!");
    }
}
