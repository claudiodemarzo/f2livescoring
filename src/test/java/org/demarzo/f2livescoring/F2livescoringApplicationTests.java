package org.demarzo.f2livescoring;

import org.demarzo.f2livescoring.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
class F2livescoringApplicationTests {

    @Test
    void testPrintWorkout() {
        Workout workout = workoutDT();

        workout.printWorkout();
    }

    private Workout workoutDT() {
        Workout workout = new Workout();
        workout.setDivision(new Division("RX Men"));
        workout.setEventNumber(1);

        workout.setName("DT");

        WorkoutBlock workoutBlock = new WorkoutBlock();
        workout.addBlock(workoutBlock);

        workoutBlock.setRounds(5);
        workoutBlock.setWorkoutBlockType(WorkoutBlockType.FOR_TIME);
        workoutBlock.setTimeCap(Duration.ofMinutes(10));

        Movement dls = new Movement("Deadlifts");
        workoutBlock.addMovement(new WorkoutBlockMovement(dls, 12, 70, Unit.KG));
        Movement hpc = new Movement("Hang Power Cleans");
        workoutBlock.addMovement(new WorkoutBlockMovement(hpc, 9, 70, Unit.KG));
        Movement pj = new Movement("Push Jerk");
        workoutBlock.addMovement(new WorkoutBlockMovement(pj, 6, 70, Unit.KG));

        return workout;
    }

}
