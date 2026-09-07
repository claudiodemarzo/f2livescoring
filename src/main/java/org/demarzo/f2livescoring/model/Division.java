package org.demarzo.f2livescoring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Division(String name) {
        log.debug("Creating Division - name={}", name);
        this.name = name;
    }

    public Division() {

    }
}
