package org.demarzo.f2livescoring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table
public class WorkoutBlockMovement {
    @Id
    private Long id;

    @ManyToOne
    private Movement movement;

    @Column
    private int reps;

    @Column
    private int volume;

    @Column
    private Unit unit;

    public WorkoutBlockMovement() {}

    public WorkoutBlockMovement(Movement movement, int reps, int volume, Unit unit) {
        log.debug("Creating WorkoutBlockMovement - movement={}, reps={}, volume={}, unit={}", 
            movement != null ? movement.getName() : "NULL", reps, volume, unit);
        this.movement = movement;
        this.reps = reps;
        this.volume = volume;
        this.unit = unit;
    }
}
