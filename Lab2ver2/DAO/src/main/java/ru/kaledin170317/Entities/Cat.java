package ru.kaledin170317.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@ToString
public class Cat {

    public Cat(CatData catData) {
        this.friends = new ArrayList<>();
        this.id = catData.getId();
        this.name = catData.getName();
        this.birthday = catData.getBirthday();
        this.breed = catData.getBreed();
        this.owner = catData.getOwner();
        this.color = catData.getColor();
        if (catData.getFriends() != null) {
//            catData.getFriends().forEach(x -> friends.add(new Cat(x)));
        }

    }

    private int id;

    private String name;
    private Date birthday;

    private String breed;

    private Color color;

    private OwnerData owner;

    private List<Cat> friends;

    public void addFriend(Cat cat) {
        friends.add(cat);
    }



}
