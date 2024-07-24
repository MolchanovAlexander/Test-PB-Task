package org.example.testpbtask.service;

import java.util.List;
import org.example.testpbtask.dto.CreateOrderRequestDto;
import org.example.testpbtask.dto.OrderResponseDto;

public interface OrderService {
    OrderResponseDto placeOrder(CreateOrderRequestDto requestDto);

    List<OrderResponseDto> getAllOrders();

    void markOrdersAsProcessed(List<Long> orderIds);
}
