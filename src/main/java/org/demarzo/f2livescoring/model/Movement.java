package org.demarzo.f2livescoring.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class Movement {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    public Movement(String name) {
        this.name = name;
    }

    public Movement() {

    }
}
