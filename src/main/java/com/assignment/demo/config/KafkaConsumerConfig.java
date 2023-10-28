package com.assignment.demo.config;

import com.assignment.demo.model.event.KafkaTransactionMessage;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value("${kafka.bootstrap-server}")
  private String bootstrapServer;

  @Value("${kafka.group-id}")
  private String groupId;

  @Bean
  public ConsumerFactory<String, List<KafkaTransactionMessage>> consumerFactory() {

    Map<String, Object> config = new HashMap<>();

    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

    ObjectMapper objectMapper = new ObjectMapper();
    JavaType type = objectMapper.getTypeFactory()
        .constructParametricType(List.class, KafkaTransactionMessage.class);

    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
        new JsonDeserializer<>(type, objectMapper, false));

  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, List<KafkaTransactionMessage>> listenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, List<KafkaTransactionMessage>> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());

    return factory;

  }

}


