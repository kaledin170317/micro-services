package ru.kaledin170317.catdb.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import ru.kaledin170317.catdb.CatService;
import ru.kaledin170317.catdb.Entites.Cat;
import ru.kaledin170317.catdb.Entites.CatData;

@Configuration
@RequiredArgsConstructor
public class Handlers {

    private final CatService catsService;

    @KafkaListener(id="server", topics = "${kafka.topic.cats.Requests}", properties = {"value.deserializer=ru.kaledin170317.catdb.kafka.CustomDeserializer"})
    @SendTo("${kafka.topic.cats.Replies}")
    public Cat listen(Cat in, @Header("operation") String operation) {
        switch (operation) {
            case "create": return new Cat(catsService.createCat(new CatData(in)));
            case "update": return new Cat(catsService.updateCat(new CatData(in)));
            case "read":  return new Cat(catsService.getCatByID(in.getId()));
            case "delete": catsService.deleteCat(new CatData(in)); return null;
        }
        throw new RuntimeException("Unknown operation: " + operation);
    }
}
