package org.demarzo.f2livescoring.controller;

import org.demarzo.f2livescoring.dto.AthleteDto;
import org.demarzo.f2livescoring.model.Athlete;
import org.demarzo.f2livescoring.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("athlete")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @GetMapping("{id}")
    public ResponseEntity<Athlete> findById(@PathVariable long id) {
        Athlete athlete = athleteService.findById(id);
        return athlete == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(athlete);
    }

    @PostMapping
    public ResponseEntity<Athlete> create(@RequestBody AthleteDto athleteDto) {
        return ResponseEntity.ok(athleteService.createAthlete(athleteDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Athlete> delete(@PathVariable long id) {
        if(athleteService.deleteAthlete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
