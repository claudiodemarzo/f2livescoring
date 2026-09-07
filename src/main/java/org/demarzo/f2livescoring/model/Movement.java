package org.demarzo.f2livescoring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        log.debug("Creating Movement - name={}", name);
        this.name = name;
    }

    public Movement() {

    }
}
