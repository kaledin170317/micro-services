package ru.kaledin170317.ownersdb.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "catsid")
public class CatsId {
    @Id
    private int catID;

}


