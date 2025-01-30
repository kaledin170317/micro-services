package ru.kaledin170317.app.Servicies;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.kaledin170317.app.Entities.Cat;
import ru.kaledin170317.app.Entities.Owner;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OwnerService {

    @Value("${kafka.topic.owners.Requests}")
    private String REQUEST_TOPIC;

    @Value("${kafka.topic.owners.Replies}")
    private String REPLIES_TOPIC;

    private final ReplyingKafkaTemplate<Integer, Owner, Owner> template;


    public Owner OwnerOperation(Owner owner, String operation) {
        try{

            ProducerRecord<Integer, Owner> record = new ProducerRecord<>(REQUEST_TOPIC, owner);
            record.headers().add(new RecordHeader("operation", operation.getBytes( )));

            RequestReplyFuture<Integer, Owner, Owner> replyFuture = template.sendAndReceive(record);
            SendResult<Integer, Owner> sendResult = replyFuture.getSendFuture().get(10, TimeUnit.SECONDS);
            ConsumerRecord<Integer, Owner> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);

            return consumerRecord.value();
        } catch (Exception ex){
            return null;
        }

    }
}
