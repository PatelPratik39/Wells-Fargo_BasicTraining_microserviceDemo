package com.wf.orderservice.service.impl;

import com.wf.orderservice.dto.OrderDTO;
import com.wf.orderservice.entity.Order;
import com.wf.orderservice.mapper.OrderMapper;
import com.wf.orderservice.repository.OrderRepository;
import com.wf.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import org.bson.Document;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MongoTemplate mongoTemplate;
    private final OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.mapToOrder(orderDTO);
        return OrderMapper.mapToOrderDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO getOrderById(String id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.mapToOrderDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::mapToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrder(String id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setPrice(orderDTO.getPrice());
        existingOrder.setQuantity(orderDTO.getQuantity());
        existingOrder.setStatus(orderDTO.getStatus());

        return OrderMapper.mapToOrderDTO(orderRepository.save(existingOrder));
    }

    @Override
    public Map<String, Long> getOrderCountByStatus() {
        try {
            Aggregation aggregation = Aggregation.newAggregation(
                    Aggregation.group("status").count().as("count")
            );

            AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "orders", Document.class);
            Map<String, Long> statusCount = new HashMap<>();

            for (Document document : results) {
                String status = document.get("_id") != null ? document.get("_id").toString() : "Unknown";
                Long count = document.getLong("count");
                statusCount.put(status, count != null ? count : 0L);
            }

            return statusCount;
        } catch (Exception e) {
            System.out.println("❌ Error while aggregating order status count:");
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}