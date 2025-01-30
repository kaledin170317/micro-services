package ru.kaledin170317.Controller;


import jakarta.persistence.EntityManagerFactory;
import lombok.NonNull;
import ru.kaledin170317.Entities.Cat;
import ru.kaledin170317.Entities.Owner;
import ru.kaledin170317.Entities.OwnerData;
import ru.kaledin170317.servicies.OwnerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OwnerController {
    private OwnerService ownerService;

    public OwnerController(EntityManagerFactory factory){
        ownerService = new OwnerService(factory);
    }
    public Owner createOwner(@NonNull String name, @NonNull Date birthday){
        OwnerData owner = OwnerData.builder()
                .birthday(birthday)
                .name(name)
                .build();
        ownerService.save(owner);
        return new Owner(owner);
    }

    public List<Owner> getAllOwners(){
        List<Owner> owners = new ArrayList<>();
        ownerService.getAllOwners().forEach(x -> owners.add(new Owner(x)));
        return owners;
    }

    public Owner getOwnerById(int id) {
        return getAllOwners().stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }
}
