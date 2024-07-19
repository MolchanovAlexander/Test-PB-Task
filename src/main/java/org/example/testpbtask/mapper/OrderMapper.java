package org.example.testpbtask.mapper;

import org.example.testpbtask.config.MapperConfig;
import org.example.testpbtask.dto.CreateOrderRequestDto;
import org.example.testpbtask.dto.OrderResponseDto;
import org.example.testpbtask.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {

    OrderResponseDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    Order toModel(CreateOrderRequestDto requestDto);
}
