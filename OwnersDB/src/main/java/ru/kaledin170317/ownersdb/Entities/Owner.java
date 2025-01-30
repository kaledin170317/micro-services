package ru.kaledin170317.ownersdb.Entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {

    public Owner(OwnerData ownerData) {
        catsID = new ArrayList<>();
        this.id = ownerData.getId();
        if (ownerData.getCatsID() != null) {
            ownerData.getCatsID().forEach(x -> catsID.add(x.getCatID()));
        }
    }

    private int id;
    private List<Integer> catsID;
}
