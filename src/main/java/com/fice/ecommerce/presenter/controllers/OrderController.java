package com.fice.ecommerce.presenter.controllers;

import com.fice.ecommerce.application.OrderService;
import com.fice.ecommerce.presenter.dto.order.CreateOrderRequestDto;
import com.fice.ecommerce.presenter.dto.order.OrderResponseDto;
import com.fice.ecommerce.presenter.dto.order.UpdateOrderRequestDto;
import com.fice.ecommerce.presenter.mapper.DtoOrderMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final DtoOrderMapper dtoOrderMapper;
    
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody @Valid CreateOrderRequestDto orderDto) {
        OrderResponseDto response = dtoOrderMapper.toDto(
            orderService.createOrder(
                dtoOrderMapper.toCreateContext(orderDto)
            )
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PatchMapping("/{orderNumber}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(
        @PathVariable @Valid UUID orderNumber,
        @RequestBody @Valid UpdateOrderRequestDto orderDto
    ) {
        OrderResponseDto response = dtoOrderMapper.toDto(
            orderService.updateOrderStatus(
                orderNumber,
                orderDto.orderStatus()
            )
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable @Valid UUID orderNumber) {
        OrderResponseDto response = dtoOrderMapper.toDto(
            orderService.getOrderByNumber(orderNumber)
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponseDto>> getCustomerOrders(
        @PathVariable @Valid UUID customerId
    ) {
        List<OrderResponseDto> response = dtoOrderMapper.toDto(
            orderService.getCustomerOrders(customerId)
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{orderNumber}")
    public ResponseEntity<OrderResponseDto> deleteOrder(@PathVariable @Valid UUID orderNumber) {
        orderService.deleteOrder(orderNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
