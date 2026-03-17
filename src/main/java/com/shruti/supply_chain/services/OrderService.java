package com.shruti.supply_chain.services;

import com.shruti.supply_chain.dto.OrderRequest;
import com.shruti.supply_chain.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(Long userId, OrderRequest request);

    List<OrderResponse> getMyOrders(Long userId);
}
