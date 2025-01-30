package ru.kaledin170317.app.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.kaledin170317.app.Entities.Cat;
import ru.kaledin170317.app.Entities.Owner;

import java.io.IOException;
import java.util.Map;


public class CustomDeserializer implements Deserializer<Object> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

//
//    @Value("${kafka.topic.cats.Replies}") нулы бля
//    private String REPLIES_TOPIC_CAT;
//
//    @Value("${kafka.topic.owners.Replies}")
//    private String REPLIES_TOPIC_OWNER;


    @Override
    public Object deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }

            if (topic.equals("cats.Replies")) {
                return objectMapper.readValue(new String(data, "UTF-8"), Cat.class);
            }

            if ( topic.equals("owners.Replies")) {
                return objectMapper.readValue(new String(data, "UTF-8"), Owner.class);
            }

            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }

    @Override
    public void close() {
    }
}
