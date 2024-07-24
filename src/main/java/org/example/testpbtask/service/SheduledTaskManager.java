package org.example.testpbtask.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.testpbtask.dto.OrderResponseDto;
import org.example.testpbtask.producer.QueueProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SheduledTaskManager {

    private static final String CRON_EXPRESSION = "0 */5 * * * *";
    private static final String TIME_ZONE = "Europe/Kiev";
    private final QueueProducer producer;
    private final OrderService orderService;

    @Scheduled(cron = CRON_EXPRESSION, zone = TIME_ZONE)
    public void regularSendBatchOfOrders() {
        List<OrderResponseDto> forPublishing = orderService.getAllOrders();
        producer.send(forPublishing);
        List<Long> orderIds = forPublishing.stream()
                .map(OrderResponseDto::getId)
                .toList();
        orderService.markOrdersAsProcessed(orderIds);
    }
}
