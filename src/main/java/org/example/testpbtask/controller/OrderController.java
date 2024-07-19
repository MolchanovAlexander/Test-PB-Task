package org.example.testpbtask.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.testpbtask.dto.CreateOrderRequestDto;
import org.example.testpbtask.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void placeOrder(@Valid @RequestBody CreateOrderRequestDto requestDto) {
        orderService.placeOrder(requestDto);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public String hello(
            @RequestParam(value = "name", defaultValue = "Dno") String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "hello";
    }
}
