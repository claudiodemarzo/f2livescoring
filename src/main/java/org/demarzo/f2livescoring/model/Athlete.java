package org.demarzo.f2livescoring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Table
@Entity
public class Athlete {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @ManyToOne(fetch = FetchType.EAGER)
    private Division division;
}
