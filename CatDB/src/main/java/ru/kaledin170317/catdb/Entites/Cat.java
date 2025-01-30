package ru.kaledin170317.catdb.Entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cat {

    public Cat(CatData catData) {
        this.id = catData.getId();
        this.name = catData.getName();
        this.birthday = catData.getBirthday();
        this.breed = catData.getBreed();
        this.color = catData.getColor();
        this.ownerID = catData.getOwnerID();
        this.friendsID = new ArrayList<>();
        if (catData.getFriends() != null) {
            catData.getFriends().forEach(x -> friendsID.add(x.getId()));
        }
    }

    private Integer id;
    private String name;
    private Date birthday;
    private String breed;
    private Color color;
    private Integer ownerID;
    private List<Integer> friendsID;
}
