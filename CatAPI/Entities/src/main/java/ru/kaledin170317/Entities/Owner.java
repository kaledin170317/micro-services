package ru.kaledin170317.Entities;

import lombok.Getter;
import lombok.ToString;
import ru.kaledin170317.DTO.OwnerData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@ToString
public class Owner {

    public Owner(OwnerData ownerData) {
        catsID = new ArrayList<>();
        this.id = ownerData.getId();
        this.name = ownerData.getUsername();
        if (ownerData.getCats() != null) {
            ownerData.getCats().forEach(x -> catsID.add(x.getId()));
        }
    }

    private Long id;
    private String name;
    private List<Integer> catsID;
}
