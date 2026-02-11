package com.tradex.trade.service.infrastructure.messaging.consumer;

import com.tradex.trade.service.application.consumer.TradeConsumerService;
import com.tradex.trade.service.domain.trade.MalformedTradeException;
import com.tradex.trade.service.domain.trade.TradeExecutedValidator;
import com.tradex.trade.service.infrastructure.messaging.kafka.TradeExecutedEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.SchemaException;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TradeExecutionConsumer {

    private final TradeConsumerService consumerService;
    private final TradeExecutedValidator tradeExecutedValidator;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(
            topics = "${kafka.topics.trades-executed}",
            groupId = "${kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(
            TradeExecutedEvent envelope
    ) {
        try{

            tradeExecutedValidator.validate(envelope);
            consumerService.consume(envelope);

        }catch (DeserializationException | SchemaException schemaEx) {
            kafkaTemplate.send("trades.executed.dlq", envelope);
        } catch (MalformedTradeException ex){
                kafkaTemplate.send("trades.executed.dlq.malformed", envelope);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object>
    kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties()
                .setAckMode(ContainerProperties.AckMode.RECORD);

        return factory;
    }



}
