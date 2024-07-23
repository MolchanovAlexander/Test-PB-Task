package org.example.testpbtask.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.testpbtask.dto.CreateOrderRequestDto;
import org.example.testpbtask.dto.OrderResponseDto;
import org.example.testpbtask.mapper.OrderMapper;
import org.example.testpbtask.model.Order;
import org.example.testpbtask.producer.QueueProducer;
import org.example.testpbtask.repository.OrderRepository;
import org.example.testpbtask.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final String CRON_EXPRESSION = "0 */5 * * * *";
    private static final String TIME_ZONE = "Europe/Kiev";
    private final QueueProducer producer;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    @Override
    public OrderResponseDto placeOrder(CreateOrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);
        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAllByProcessedNot().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Scheduled(cron = CRON_EXPRESSION, zone = TIME_ZONE)
    @Transactional
    public void regularSendBatchOfOrders() {
        List<OrderResponseDto> forPublishing = getAllOrders();
        producer.send(forPublishing);
        List<Long> orderIds = forPublishing.stream()
                .map(OrderResponseDto::getId)
                .toList();
        orderRepository.markOrdersAsProcessed(orderIds);
    }
}
