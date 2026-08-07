package org.demarzo.f2livescoring.service;

import lombok.extern.slf4j.Slf4j;
import org.demarzo.f2livescoring.model.Division;
import org.demarzo.f2livescoring.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DivisionService {
    @Autowired
    private DivisionRepository divisionRepository;

    public Division getOrCreateByName(String name) {
        log.info("getOrCreateByName name={}", name);
        Division division = divisionRepository.findByName(name);
        if (division == null) {
            log.info("Division not found - creating it, name={}", name);
            division = new Division();
            division.setName(name);
            division = divisionRepository.save(division);
        }
        return division;
    }
}
