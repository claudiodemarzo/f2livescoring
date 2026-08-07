package org.demarzo.f2livescoring.repository;

import org.demarzo.f2livescoring.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}
