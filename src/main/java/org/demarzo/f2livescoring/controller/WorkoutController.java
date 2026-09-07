package org.demarzo.f2livescoring.controller;

import lombok.extern.slf4j.Slf4j;
import org.demarzo.f2livescoring.dto.WorkoutDto;
import org.demarzo.f2livescoring.model.Workout;
import org.demarzo.f2livescoring.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("workout")
public class WorkoutController {
    @Autowired
    private WorkoutService service;

    @GetMapping
    private ResponseEntity<List<Workout>> getAllWorkouts() {
        log.info("GET /workout - Fetching all workouts");
        List<Workout> workouts = service.getAllWorkouts();
        log.debug("Found {} workouts", workouts.size());
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("{id}")
    private ResponseEntity<Workout> getWorkout(@PathVariable long id) {
        log.info("GET /workout/{} - Find workout by ID", id);
        Optional<Workout> workoutOptional = service.findById(id);
        if (workoutOptional.isEmpty()) {
            log.warn("Workout with ID {} not found", id);
        } else {
            log.debug("Found workout: {}", workoutOptional.get().getName());
        }
        return workoutOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    private ResponseEntity<Workout> createWorkout(@RequestBody WorkoutDto workout) {
        log.info("POST /workout - Creating new workout: {}", workout.getDivisionName());
        try {
            Workout newWorkout = service.createWorkoutByDto(workout);
            log.info("Successfully created workout with ID {}", newWorkout.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(newWorkout);
        } catch (Exception e) {
            log.error("Error creating workout: {}", workout.getDivisionName(), e);
            throw e;
        }
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Boolean> deleteWorkout(@PathVariable long id) {
        log.info("DELETE /workout/{} - Deleting workout", id);
        try {
            boolean deleted = service.deleteById(id);
            if (deleted) {
                log.info("Successfully deleted workout with ID {}", id);
            } else {
                log.warn("Workout with ID {} not found or already deleted", id);
            }
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            log.error("Error deleting workout with ID {}", id, e);
            throw e;
        }
    }

}
