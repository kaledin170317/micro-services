package ru.kaledin170317.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Owner")
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerData {

    public OwnerData(Owner ownerData) {
        this.id = ownerData.getId();
        this.name = ownerData.getName();
        this.birthday = ownerData.getBirthday();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private Date birthday;
}
