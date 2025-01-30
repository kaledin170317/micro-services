package ru.kaledin170317.ownersdb.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import ru.kaledin170317.ownersdb.Entities.Owner;
import ru.kaledin170317.ownersdb.Entities.OwnerData;
import ru.kaledin170317.ownersdb.OwnerService;

@Configuration
@RequiredArgsConstructor
public class KafkaHandlers {

    private final OwnerService ownerService;

    @KafkaListener(id = "server", topics = "${kafka.topic.owners.Requests}",
            properties = {"value.deserializer=ru.kaledin170317.ownersdb.kafka.CustomDeserializer"})
    @SendTo("${kafka.topic.owners.Replies}")
    public Owner listen(Owner in, @Header("operation") String operation) {
        switch (operation) {
            case "create": return new Owner(ownerService.createOwner(new OwnerData(in)));
            case "update": return new Owner(ownerService.updateOwner(new OwnerData(in)));
            case "read": return new Owner(ownerService.getOwnerByID(in.getId()));
            case "delete": ownerService.deleteOwner(new OwnerData(in)); return null;
            default: throw new RuntimeException("Invalid operation: " + operation);
        }
    }
}
