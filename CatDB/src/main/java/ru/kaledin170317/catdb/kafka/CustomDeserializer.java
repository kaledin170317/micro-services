package ru.kaledin170317.catdb.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import ru.kaledin170317.catdb.Entites.Cat;

import java.util.Map;

//@Slf4j
public class CustomDeserializer implements Deserializer<Cat> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Cat deserialize(String topic, byte[] data) {

        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing..." + data + " to " + objectMapper.readValue(new String(data, "UTF-8"), Cat.class));
            return objectMapper.readValue(new String(data, "UTF-8"), Cat.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }

    @Override
    public void close() {
    }
}
