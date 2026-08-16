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
    private String name;

    @Column
    private Integer eventNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<WorkoutBlock> blocks;

    @ManyToOne(fetch = FetchType.EAGER)
    private Division division;

    public void addBlock(WorkoutBlock workoutBlock) {
        if(blocks == null) {
            blocks = new ArrayList<>();
        }

        blocks.add(workoutBlock);
    }

    public void printWorkout() {
        System.out.println(eventNumber + " \"" + name + "\" - Division: " + division.getName());
        for (int i  = 0; i < blocks.size(); i++) {
            WorkoutBlock block = blocks.get(i);
            System.out.println("Block " + (i + 1) + " - " + block.getTimeCap().getSeconds() + "s - " + block.getRounds() + " Rounds " + block.getWorkoutBlockType().name());
            System.out.println("-- MOVEMENTS --");
            for(WorkoutBlockMovement movement : block.getMovements()) {
                System.out.println(movement.getReps() + " " + movement.getMovement().getName() + " (" + movement.getVolume() + movement.getUnit() + ")");
            }
        }
    }
}
