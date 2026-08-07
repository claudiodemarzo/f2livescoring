package org.demarzo.f2livescoring.repository;

import org.demarzo.f2livescoring.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
