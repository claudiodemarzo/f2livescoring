package org.demarzo.f2livescoring.controller;

import lombok.extern.slf4j.Slf4j;
import org.demarzo.f2livescoring.dto.AthleteDto;
import org.demarzo.f2livescoring.model.Athlete;
import org.demarzo.f2livescoring.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("athlete")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @GetMapping("{id}")
    public ResponseEntity<Athlete> findById(@PathVariable long id) {
        log.info("GET /athlete/{} - Find athlete by ID", id);
        Athlete athlete = athleteService.findById(id);
        if (athlete == null) {
            log.warn("Athlete with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
        log.debug("Found athlete: {}", athlete.getName());
        return ResponseEntity.ok(athlete);
    }

    @PostMapping
    public ResponseEntity<Athlete> create(@RequestBody AthleteDto athleteDto) {
        log.info("POST /athlete - Creating new athlete: {} {}", athleteDto.getName(), athleteDto.getSurname());
        try {
            Athlete created = athleteService.createAthlete(athleteDto);
            log.info("Successfully created athlete with ID {}", created.getId());
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            log.error("Error creating athlete: {} {}", athleteDto.getName(), athleteDto.getSurname(), e);
            throw e;
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Athlete> delete(@PathVariable long id) {
        log.info("DELETE /athlete/{} - Deleting athlete", id);
        if(athleteService.deleteAthlete(id)) {
            log.info("Successfully deleted athlete with ID {}", id);
            return ResponseEntity.ok().build();
        } else {
            log.warn("Failed to delete athlete - ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("by-division/{divisionId}")
    public ResponseEntity<List<Athlete>> findByDivision(@PathVariable long divisionId) {
        log.info("GET /athlete/by-division/{} - Finding athletes by division", divisionId);
        List<Athlete> athletes = athleteService.findByDivision(divisionId);
        log.debug("Found {} athletes in division {}", athletes.size(), divisionId);
        return ResponseEntity.ok(athletes);
    }
}
