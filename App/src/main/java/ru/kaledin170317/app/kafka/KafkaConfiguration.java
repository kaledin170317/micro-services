package ru.kaledin170317.app.kafka;
import java.util.HashMap;
import java.util.Map;

//import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import ru.kaledin170317.app.Entities.Cat;
import ru.kaledin170317.app.Entities.Owner;

@Configuration
@EnableKafka
public class KafkaConfiguration {

    @Value("${kafka.topic.cats.Replies}")
    private String REPLIES_TOPIC_CAT;

    @Value("${kafka.topic.owners.Replies}")
    private String REPLIES_TOPIC_OWNER;

    @Value("${kafka.reply.group:repliesGroup-0}")
    private String replyGroup;
    @Bean
    ConcurrentKafkaListenerContainerFactory<Integer, Cat> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, Cat> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, Cat> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<Integer, Owner> kafkaListenerContainerFactory2() {
        ConcurrentKafkaListenerContainerFactory<Integer, Owner> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory2());
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, Owner> consumerFactory2() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "ru.kaledin170317.app.kafka.CustomDeserializer");
        return props;
    }

    @Bean
    public KafkaProperties.Listener listener() {
        return new KafkaProperties.Listener();
    }

    @Bean
    public ProducerFactory<Integer, Cat> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public ProducerFactory<Integer, Owner> producerFactory2() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"ru.kaledin170317.app.kafka.CustomSerializer");
        return props;
    }

    @Bean
    public KafkaTemplate<Integer, Cat> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<Integer, Owner> kafkaTemplate2() {
        return new KafkaTemplate<>(producerFactory2());
    }


    @Bean
    public ReplyingKafkaTemplate<Integer, Owner, Owner> replyingTemplate2(
            ProducerFactory<Integer, Owner> pf,
            ConcurrentMessageListenerContainer<Integer, Owner> repliesContainer) {

        ReplyingKafkaTemplate<Integer, Owner, Owner> template = new ReplyingKafkaTemplate<>(pf, repliesContainer);
        template.setSharedReplyTopic(true);
        return template;
    }

    @Bean
    public ConcurrentMessageListenerContainer<Integer, Owner> repliesContainer2(
            ConcurrentKafkaListenerContainerFactory<Integer, Owner> containerFactory) {

        ConcurrentMessageListenerContainer<Integer, Owner> repliesContainer =
                containerFactory.createContainer(REPLIES_TOPIC_OWNER);
        repliesContainer.getContainerProperties().setGroupId(replyGroup); // Overrides any `group.id` property provided by the consumer factory configuration
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }
    @Bean
    public ReplyingKafkaTemplate<Integer, Cat, Cat> replyingTemplate(
            ProducerFactory<Integer, Cat> pf,
            ConcurrentMessageListenerContainer<Integer, Cat> repliesContainer) {

        ReplyingKafkaTemplate<Integer, Cat, Cat> template = new ReplyingKafkaTemplate<>(pf, repliesContainer);
        template.setSharedReplyTopic(true);
        return template;
    }

    @Bean
    public ConcurrentMessageListenerContainer<Integer, Cat> repliesContainer(
            ConcurrentKafkaListenerContainerFactory<Integer, Cat> containerFactory) {

        ConcurrentMessageListenerContainer<Integer, Cat> repliesContainer =
                containerFactory.createContainer(REPLIES_TOPIC_CAT);
        repliesContainer.getContainerProperties().setGroupId(replyGroup); // Overrides any `group.id` property provided by the consumer factory configuration
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }
}
