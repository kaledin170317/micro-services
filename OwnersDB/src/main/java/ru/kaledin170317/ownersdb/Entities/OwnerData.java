package ru.kaledin170317.ownersdb.Entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;

import java.util.List;

@Entity
//@Builder
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "owners")
public class OwnerData {

    public OwnerData(int id) {
        this.id = id;
    }

    public OwnerData(Owner ownerData) {
        this.catsID = new ArrayList<>();
        this.id = ownerData.getId();
        if (ownerData.getCatsID() != null) {
            ownerData.getCatsID().forEach(x -> catsID.add(new CatsId(x)));
        }
    }



    @Id
    private int id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<CatsId> catsID;


}


