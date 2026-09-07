package org.demarzo.f2livescoring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        log.debug("Adding block to workout - id={}, blockType={}", this.id, workoutBlock.getWorkoutBlockType());
        if(blocks == null) {
            blocks = new ArrayList<>();
            log.debug("Initialized blocks list for workout");
        }
        blocks.add(workoutBlock);
        log.debug("Block added successfully - total blocks: {}", blocks.size());
    }

    public void printWorkout() {
        log.info("Printing workout - id={}, name={}, event={}, division={}", 
            this.id, this.name, this.eventNumber, 
            this.division != null ? this.division.getName() : "N/A");
        
        System.out.println(eventNumber + " \"" + name + "\" - Division: " + 
            (division != null ? division.getName() : "N/A"));
        
        if (blocks != null) {
            for (int i  = 0; i < blocks.size(); i++) {
                WorkoutBlock block = blocks.get(i);
                log.debug("Printing block {} - type={}, rounds={}, timeCap={}s", 
                    i + 1, block.getWorkoutBlockType(), block.getRounds(), 
                    block.getTimeCap() != null ? block.getTimeCap().getSeconds() : "N/A");
                
                System.out.println("Block " + (i + 1) + " - " + 
                    (block.getTimeCap() != null ? block.getTimeCap().getSeconds() : "N/A") + 
                    "s - " + block.getRounds() + " Rounds " + block.getWorkoutBlockType().name());
                System.out.println("-- MOVEMENTS --");
                
                if (block.getMovements() != null) {
                    for(WorkoutBlockMovement movement : block.getMovements()) {
                        System.out.println(movement.getReps() + " " + movement.getMovement().getName() + 
                            " (" + movement.getVolume() + movement.getUnit() + ")");
                    }
                    log.debug("Printed {} movements from block {}", block.getMovements().size(), i + 1);
                }
            }
        }
    }
}
