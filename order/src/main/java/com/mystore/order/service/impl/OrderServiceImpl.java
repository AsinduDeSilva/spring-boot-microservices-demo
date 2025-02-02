package com.mystore.order.service.impl;

import com.mystore.inventory.dto.InventoryDTO;
import com.mystore.order.common.ErrorOrderResponse;
import com.mystore.order.common.OrderResponse;
import com.mystore.order.common.SuccessOrderResponse;
import com.mystore.order.model.Orders;
import com.mystore.order.dto.OrderDTO;
import com.mystore.order.repo.OrderRepo;
import com.mystore.order.service.OrderService;
import com.mystore.product.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final WebClient inventoryWebClient;

    private final WebClient productWebClient;

    private final OrderRepo orderRepo;

    private final ModelMapper modelMapper;

    public OrderServiceImpl(WebClient inventoryWebClient, WebClient productWebClient, OrderRepo orderRepo, ModelMapper modelMapper) {
        this.inventoryWebClient = inventoryWebClient;
        this.productWebClient = productWebClient;
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Orders>orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    @Override
    public OrderResponse saveOrder(OrderDTO orderDTO) {
        try{
            InventoryDTO inventoryResponse = inventoryWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/{itemId}").build(orderDTO.getItemId()))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            assert inventoryResponse != null;

            ProductDTO productResponse = productWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/{productId}").build(inventoryResponse.getProductId()))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();

            assert productResponse != null;
            System.out.println(productResponse);

            if(inventoryResponse.getQuantity() > 0){
                if(productResponse.getForSale()){
                    orderRepo.save(modelMapper.map(orderDTO, Orders.class));
                    return new SuccessOrderResponse(orderDTO);
                }else{
                    return new ErrorOrderResponse("Item is not for sale");
                }

            }else{
                return new ErrorOrderResponse("Item not available");
            }
        }catch(WebClientResponseException ex){
            if(ex.getStatusCode().is5xxServerError()){
                return new ErrorOrderResponse("Item not found");
            }
            return new ErrorOrderResponse("Error");
        }
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Orders.class));
        return orderDTO;
    }

    @Override
    public String deleteOrder(Integer orderId) {
        orderRepo.deleteById(orderId);
        return "Order deleted";
    }

    @Override
    public OrderDTO getOrderById(Integer orderId) {
        Orders order = orderRepo.getOrderById(orderId);
        return modelMapper.map(order, OrderDTO.class);
    }
}
