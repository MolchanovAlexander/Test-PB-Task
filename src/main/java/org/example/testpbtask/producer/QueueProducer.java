package org.example.testpbtask.producer;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.example.testpbtask.dto.OrderResponseDto;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class QueueProducer {
    public void send(List<OrderResponseDto> forPublishing) {
        log.info("Producer has been called, number of messages: {}", forPublishing.size());
    }
}
