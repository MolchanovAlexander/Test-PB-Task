package org.example.testpbtask.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.testpbtask.dto.CreateOrderRequestDto;
import org.example.testpbtask.dto.OrderResponseDto;
import org.example.testpbtask.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> addOrder(
            @Valid @RequestBody CreateOrderRequestDto requestDto
    ) {
        OrderResponseDto orderResponseDto = orderService.placeOrder(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }

    @GetMapping
    public String viewBooks() {
        log.info("Received request to view books");
        try {
            return "view-orders";
        } catch (Exception e) {
            log.error(e);
        } finally {
            return "view-orders";
        }
    }
}
