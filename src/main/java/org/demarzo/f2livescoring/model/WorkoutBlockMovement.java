package org.demarzo.f2livescoring.model;

import jakarta.persistence.*;
import lombok.Data;

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
        this.movement = movement;
        this.reps = reps;
        this.volume = volume;
        this.unit = unit;
    }
}
