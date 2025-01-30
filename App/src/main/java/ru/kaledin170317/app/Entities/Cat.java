package ru.kaledin170317.app.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cat implements Serializable {

    public Cat(int id) {
        this.id = id;
    }
    private int id;
    private String name;
    private Date birthday;
    private String breed;
    private Color color;
    private int ownerID;
    private List<Integer> friendsID;

    public void addFriend(int friendID) {
        friendsID.add(friendID);
    }
}
