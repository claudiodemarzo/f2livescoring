package org.demarzo.f2livescoring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String workoutName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<WorkoutBlock> workoutBlocks;

    @ManyToOne(fetch = FetchType.EAGER)
    private Division division;

    public void addBlock(WorkoutBlock workoutBlock) {
        if(workoutBlocks == null) {
            workoutBlocks = new ArrayList<>();
        }

        workoutBlocks.add(workoutBlock);
    }

    public void printWorkout() {
        System.out.println("\"" + workoutName + "\" - Division: " + division.getName());
        for (int i  = 0; i < workoutBlocks.size(); i++) {
            WorkoutBlock block = workoutBlocks.get(i);
            System.out.println("Block " + (i + 1) + " - " + block.getTimeCap().getSeconds() + "s - " + block.getRounds() + " Rounds " + block.getWorkoutBlockType().name());
            System.out.println("-- MOVEMENTS --");
            for(WorkoutBlockMovement movement : block.getMovements()) {
                System.out.println(movement.getReps() + " " + movement.getMovement().getName() + " (" + movement.getVolume() + movement.getUnit() + ")");
            }
        }
    }
}
