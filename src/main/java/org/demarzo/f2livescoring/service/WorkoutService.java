package org.demarzo.f2livescoring.service;

import lombok.extern.slf4j.Slf4j;
import org.demarzo.f2livescoring.dto.WorkoutDto;
import org.demarzo.f2livescoring.model.Workout;
import org.demarzo.f2livescoring.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    public List<Workout> getAllWorkouts() {
        log.debug("Fetching all workouts");
        List<Workout> workouts = workoutRepository.findAll();
        log.debug("Found {} workouts", workouts.size());
        return workouts;
    }

    public Optional<Workout> findById(long id) {
        log.debug("Finding Workout by id {}", id);
        Optional<Workout> workout = workoutRepository.findById(id);
        if (workout.isPresent()) {
            log.debug("Found workout: {} - Event {} (id={})", workout.get().getName(), workout.get().getEventNumber(), id);
        } else {
            log.debug("Workout with id {} not found", id);
        }
        return workout;
    }

    public Workout createWorkoutByDto(WorkoutDto workout) {
        log.debug("Creating Workout - divisionName={}", workout.getDivisionName());
        Workout newWorkout = new Workout();
        newWorkout.setName(workout.getDivisionName());
        newWorkout = workoutRepository.save(newWorkout);
        log.info("Successfully created workout with id {}", newWorkout.getId());
        return newWorkout;
    }

    public boolean deleteById(long id) {
        log.debug("Deleting Workout by id {}", id);
        Optional<Workout> workout = workoutRepository.findById(id);
        if (workout.isPresent()) {
            workoutRepository.deleteById(id);
            log.info("Successfully deleted workout: {} (id={})", workout.get().getName(), id);
            return true;
        } else {
            log.warn("Workout with id {} not found, cannot delete", id);
            return false;
        }
    }
}