package ru.kaledin170317.app.Entities;

import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {

    public Owner(int id) {
        this.id = id;
    }
    public void AddCatId(int id) {
        catsID.add(id);
    }
    private int id;
    private List<Integer> catsID;
}
