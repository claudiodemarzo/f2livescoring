package org.demarzo.f2livescoring.repository;

import org.demarzo.f2livescoring.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("select m from Movement m where lower(m.name) like lower(concat('%', :name,'%'))")
    List<Movement> findMovementLooseMatchName(@Param("name") String name);

    Movement findMovementByName(String name);
}
