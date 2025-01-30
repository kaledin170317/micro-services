package ru.kaledin170317.catdb.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Cat")
public class CatData {

    public CatData(int id) {
        this.id = id;
    }

    public CatData(Cat cat) {
        this.friends = new ArrayList<>();
        this.id = cat.getId();
        this.name = cat.getName();
        this.birthday = cat.getBirthday();
        this.breed = cat.getBreed();
        this.ownerID = cat.getOwnerID();
        if (cat.getFriendsID() != null) {
            cat.getFriendsID().forEach(x -> friends.add(new CatData(x)));
        }
        this.color = cat.getColor();
    }

    @Id
    @GeneratedValue()
    private int id;

    private String name;
    private Date birthday;
    private String breed;

    @Enumerated(EnumType.STRING)
    private Color color;

    private Integer ownerID;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<CatData> friends;

}
