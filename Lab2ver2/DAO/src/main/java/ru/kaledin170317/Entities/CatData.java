package ru.kaledin170317.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cat")
public class CatData {

    public CatData(Cat cat) {
        this.friends = new ArrayList<>();
        this.id = cat.getId();
        this.name = cat.getName();
        this.birthday = cat.getBirthday();
        this.breed = cat.getBreed();
        this.owner = cat.getOwner();
        if (cat.getFriends() != null) {
            cat.getFriends().forEach(x -> friends.add(new CatData(x)));
        }
        this.color = cat.getColor();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private Date birthday;

    private String breed;

    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne
    private OwnerData owner;

    @Getter
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<CatData> friends;

    public void AddFriend(CatData data) {
        friends.add(data);
    }
}
