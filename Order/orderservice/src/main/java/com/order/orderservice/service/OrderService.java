package com.order.orderservice.service;

import com.order.orderservice.model.entity.Order;
import com.order.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public boolean createOrder(Order Order) {
        if(Order != null) {
            orderRepository.save(Order);
            return true;
        }

        return false;
    }

    public List<Order> readOrder() {
        return orderRepository.findAll();
    }

    public boolean updateOrder(int id, Order order) {
        var data = orderRepository.findById(id);
        if(data.isPresent()) {
            data.get().setTotalProduct(order.getTotalPrice());
            data.get().setTotalPrice(order.getTotalPrice());

            orderRepository.save(data.get());
            return true;
        }

        return false;
    }

    public boolean deleteOrder(int id) {
        var data = orderRepository.findById(id);
        if(data.isPresent()) {
            orderRepository.delete(data.get());
            return true;
        }

        return false;
    }
}
