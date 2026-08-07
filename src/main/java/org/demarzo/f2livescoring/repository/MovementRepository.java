package org.demarzo.f2livescoring.repository;

import org.demarzo.f2livescoring.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long> {
}
