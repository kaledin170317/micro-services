package ru.kaledin170317.app.Servicies;


import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.kaledin170317.app.Entities.Cat;
import ru.kaledin170317.app.Entities.Owner;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CatService {

    @Value("${kafka.topic.cats.Requests}")
    private String REQUEST_TOPIC;

    @Value("${kafka.topic.cats.Replies}")
    private String REPLIES_TOPIC;

    private final ReplyingKafkaTemplate<Integer, Cat, Cat> template;


    public Cat CatOperations(Cat cat, String operation) {
        try{

            ProducerRecord<Integer, Cat> record = new ProducerRecord<>(REQUEST_TOPIC, cat);
            record.headers().add(new RecordHeader("operation", operation.getBytes( )));

            RequestReplyFuture<Integer, Cat, Cat> replyFuture = template.sendAndReceive(record);
            SendResult<Integer, Cat> sendResult = replyFuture.getSendFuture().get(10, TimeUnit.SECONDS);
            ConsumerRecord<Integer, Cat> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);

            return consumerRecord.value();
        } catch (Exception ex){
            return null;
        }
    }
}
