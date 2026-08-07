package org.demarzo.f2livescoring.service;

import lombok.extern.slf4j.Slf4j;
import org.demarzo.f2livescoring.dto.AthleteDto;
import org.demarzo.f2livescoring.model.Athlete;
import org.demarzo.f2livescoring.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private DivisionService divisionService;

    public Athlete findById(Long id) {
        log.debug("Finding Athlete by id {}", id);
        return athleteRepository.findById(id).orElse(null);
    }

    public Athlete createAthlete(AthleteDto dto) {
        log.debug("Creating Athlete {}", dto);
        Athlete athlete = new Athlete();
        athlete.setName(dto.getName());
        athlete.setSurname(dto.getSurname());
        athlete.setDivision(divisionService.getOrCreateByName(dto.getDivision()));

        athlete = athleteRepository.save(athlete);
        return athlete;
    }

    public boolean deleteAthlete(Long id) {
        log.debug("Deleting Athlete by id {}", id);
        Athlete athlete = athleteRepository.findById(id).orElse(null);
        if(athlete == null) return false;
        athleteRepository.delete(athlete);
        return true;
    }
}
