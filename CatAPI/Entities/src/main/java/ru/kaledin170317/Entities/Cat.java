package ru.kaledin170317.Entities;

import lombok.Getter;
import lombok.ToString;
import ru.kaledin170317.DTO.CatData;
import ru.kaledin170317.DTO.OwnerData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@ToString
public class Cat {

    public Cat(CatData catData) {
        this.friendsID = new ArrayList<>();
        this.id = catData.getId();
        this.name = catData.getName();
        this.birthday = catData.getBirthday();
        this.breed = catData.getBreed();
        this.ownerID =  catData.getOwner().getId();
        this.color = catData.getColor();
        if (catData.getFriends() != null) {
            catData.getFriends().forEach(x -> friendsID.add(x.getId()));
        }
    }

    private int id;
    private String name;
    private Date birthday;
    private String breed;
    private Color color;
    private Long ownerID;
    private List<Integer> friendsID;

}
