package com.shruti.supply_chain.services;

import com.shruti.supply_chain.dto.OrderRequest;
import com.shruti.supply_chain.dto.OrderResponse;
import com.shruti.supply_chain.model.ApprovalStatus;
import com.shruti.supply_chain.model.Order;
import com.shruti.supply_chain.model.SupplierProduct;
import com.shruti.supply_chain.model.User;
import com.shruti.supply_chain.repository.OrderRepository;
import com.shruti.supply_chain.repository.SupplierProductRepository;
import com.shruti.supply_chain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final SupplierProductRepository supplierProductRepository;
    private final OrderRepository orderRepository;


    @Override
    public OrderResponse placeOrder(Long userId, OrderRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        SupplierProduct sp = supplierProductRepository.findById(request.getSupplierProductId())
                .orElseThrow(() -> new RuntimeException("Supplier product not found"));

        if (sp.getApprovalStatus() != ApprovalStatus.APPROVED) {
            throw new RuntimeException("Product not approved yet");
        }

        double totalPrice = sp.getSupplyPrice().doubleValue() * request.getQuantity();

        Order order = Order.builder()
                .user(user)
                .supplierProduct(sp)
                .quantity(request.getQuantity())
                .totalPrice(totalPrice)
                .build();

        orderRepository.save(order);

        return mapToResponse(order);
    }

    @Override
    public List<OrderResponse> getMyOrders(Long userId) {

        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private OrderResponse mapToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .productName(order.getSupplierProduct().getProduct().getName())
                .supplierName(order.getSupplierProduct().getSupplier().getUser().getName())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .build();
    }
}
