package com.mystore.inventory.kafka;

import com.example.base.dto.OrderEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderEventDTO.class);

    @KafkaListener(
            topics = "${spring.kafka.template.default-topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEventDTO orderEventDTO){
        logger.info("Receiving order event from topic: {}", orderEventDTO);
    }
}
