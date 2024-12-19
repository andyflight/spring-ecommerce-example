package com.fice.ecommerce.presenter.controllers;

import com.fice.ecommerce.application.OrderService;
import com.fice.ecommerce.presenter.dto.order.CreateOrderRequestDto;
import com.fice.ecommerce.presenter.dto.order.OrderResponseDto;
import com.fice.ecommerce.presenter.dto.order.UpdateOrderRequestDto;
import com.fice.ecommerce.presenter.mapper.DtoOrderMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Orders", description = "API for managing orders")
public class OrderController {
    private final OrderService orderService;
    private final DtoOrderMapper dtoOrderMapper;

    @Operation(
            summary = "Create a new order",
            description = "This endpoint creates a new order based on the provided details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Details of the order to create",
                    required = true
            )
    )
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody @Valid CreateOrderRequestDto orderDto) {
        OrderResponseDto response = dtoOrderMapper.toDto(
            orderService.createOrder(
                dtoOrderMapper.toCreateContext(orderDto)
            )
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update order status",
            description = "This endpoint updates the status of an existing order.",
            parameters = @Parameter(name = "orderNumber", description = "The unique identifier of the order", required = true)
    )
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

    @Operation(
            summary = "Retrieve order by number",
            description = "This endpoint fetches an order based on its unique identifier.",
            parameters = @Parameter(name = "orderNumber", description = "The unique identifier of the order", required = true)
    )
    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable @Valid UUID orderNumber) {
        OrderResponseDto response = dtoOrderMapper.toDto(
            orderService.getOrderByNumber(orderNumber)
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve orders by customer ID",
            description = "This endpoint fetches all orders associated with a specific customer.",
            parameters = @Parameter(name = "customerId", description = "The unique identifier of the customer", required = true)
    )
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponseDto>> getCustomerOrders(
        @PathVariable @Valid UUID customerId
    ) {
        List<OrderResponseDto> response = dtoOrderMapper.toDto(
            orderService.getCustomerOrders(customerId)
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete an order",
            description = "This endpoint deletes an order based on its unique identifier.",
            parameters = @Parameter(name = "orderNumber", description = "The unique identifier of the order", required = true)
    )
    @DeleteMapping("/{orderNumber}")
    public ResponseEntity<OrderResponseDto> deleteOrder(@PathVariable @Valid UUID orderNumber) {
        orderService.deleteOrder(orderNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
