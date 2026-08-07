package org.demarzo.f2livescoring.repository;

import org.demarzo.f2livescoring.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivisionRepository extends JpaRepository <Division,Long> {
    Division findByName(String name);
}
