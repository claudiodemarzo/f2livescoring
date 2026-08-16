package org.demarzo.f2livescoring.repository;

import org.demarzo.f2livescoring.model.Athlete;
import org.demarzo.f2livescoring.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    public List<Athlete> findAllByDivision(Division d);
}
