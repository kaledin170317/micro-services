package ru.kaledin170317.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class Owner {

    public Owner(OwnerData ownerData) {
        this.id = ownerData.getId();
        this.name = ownerData.getName();
        this.birthday = ownerData.getBirthday();
    }

    private int id;

    private String name;

    private Date birthday;
}
