package ru.kaledin170317.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import ru.kaledin170317.app.Entities.Cat;
import ru.kaledin170317.app.Entities.Owner;

@SpringBootApplication
@Log4j2
public class AppApplication {


    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${kafka.request.message:foo}")
    private String requestMessage;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
