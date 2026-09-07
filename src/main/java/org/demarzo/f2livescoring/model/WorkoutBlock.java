package org.demarzo.f2livescoring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        log.debug("Adding movement to block - id={}, movement={}, reps={}", 
            this.id, movement.getMovement().getName(), movement.getReps());
        if(movements == null) {
            movements = new ArrayList<>();
            log.debug("Initialized movements list for block");
        }

        movements.add(movement);
        log.debug("Movement added successfully - total movements: {}", movements.size());
    }
}
