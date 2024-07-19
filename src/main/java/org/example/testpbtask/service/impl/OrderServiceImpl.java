package org.example.testpbtask.service.impl;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.testpbtask.dto.CreateOrderRequestDto;
import org.example.testpbtask.model.Order;
import org.example.testpbtask.repository.OrderRepository;
import org.example.testpbtask.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public void placeOrder( CreateOrderRequestDto requestDto) {
        Order order = new Order();
        orderRepository.save(order);

    }

    @Override
    public List<Order> getAllOrders() {
            return orderRepository.findAll();
    }
}