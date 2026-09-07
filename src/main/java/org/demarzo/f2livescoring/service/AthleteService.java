package org.demarzo.f2livescoring.service;

import lombok.extern.slf4j.Slf4j;
import org.demarzo.f2livescoring.dto.AthleteDto;
import org.demarzo.f2livescoring.model.Athlete;
import org.demarzo.f2livescoring.model.Division;
import org.demarzo.f2livescoring.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private DivisionService divisionService;

    public Athlete findById(Long id) {
        log.debug("Finding Athlete by id {}", id);
        Optional<Athlete> athlete = athleteRepository.findById(id);
        if (athlete.isPresent()) {
            log.debug("Found athlete: {} {}", athlete.get().getName(), athlete.get().getSurname());
        } else {
            log.debug("Athlete with id {} not found", id);
        }
        return athlete.orElse(null);
    }

    public Athlete createAthlete(AthleteDto dto) {
        log.debug("Creating Athlete {} {}", dto.getName(), dto.getSurname());
        Athlete athlete = new Athlete();
        athlete.setName(dto.getName());
        athlete.setSurname(dto.getSurname());
        
        Division division = divisionService.getOrCreateByName(dto.getDivision());
        log.debug("Assigned division: {}", division.getName());
        athlete.setDivision(division);

        athlete = athleteRepository.save(athlete);
        log.info("Successfully created athlete with id {}", athlete.getId());
        return athlete;
    }

    public boolean deleteAthlete(Long id) {
        log.debug("Deleting Athlete by id {}", id);
        Optional<Athlete> athlete = athleteRepository.findById(id);
        if(athlete.isEmpty()) {
            log.warn("Athlete with id {} not found, cannot delete", id);
            return false;
        }
        athleteRepository.delete(athlete.get());
        log.info("Successfully deleted athlete with id {}", id);
        return true;
    }

    public List<Athlete> findByDivision(long divisionId) {
        log.debug("Finding Athletes by division {}", divisionId);
        Optional<Division> division = divisionService.getById(divisionId);
        if(division.isEmpty()) {
            log.warn("Division with id {} not found", divisionId);
            return new ArrayList<>();
        }
        List<Athlete> athletes = athleteRepository.findAllByDivision(division.get());
        log.debug("Found {} athletes in division {}", athletes.size(), division.get().getName());
        return athletes;
    }
}
