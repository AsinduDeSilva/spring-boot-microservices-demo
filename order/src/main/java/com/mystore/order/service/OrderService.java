package com.mystore.order.service;

import com.mystore.order.common.OrderResponse;
import com.mystore.order.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();

    OrderResponse saveOrder(OrderDTO OrderDTO);

    OrderDTO updateOrder(OrderDTO OrderDTO);

    String deleteOrder(Integer orderId);

    OrderDTO getOrderById(Integer orderId);


}
