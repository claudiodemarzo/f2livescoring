package org.demarzo.f2livescoring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@Table
@Entity
public class WorkoutBlock {
    @Id
    private long id;

    @Column
    private WorkoutBlockType workoutBlockType;

    @OneToMany
    private List<WorkoutBlockMovement> movements;

    @Column
    private int rounds;

    @Column
    private Duration timeCap;

    public void addMovement(WorkoutBlockMovement movement) {
        if(movements == null) {
            movements = new ArrayList<>();
        }

        movements.add(movement);
    }
}
