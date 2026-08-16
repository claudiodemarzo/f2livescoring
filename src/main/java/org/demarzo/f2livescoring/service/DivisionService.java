package org.demarzo.f2livescoring.service;

import lombok.extern.slf4j.Slf4j;
import org.demarzo.f2livescoring.dto.DivisionDto;
import org.demarzo.f2livescoring.model.Division;
import org.demarzo.f2livescoring.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Division> getById(long id) {
        return divisionRepository.findById(id);
    }

    public void deleteById(long id) {
        divisionRepository.deleteById(id);
    }

    public List<Division> getAll() {
        return divisionRepository.findAll();
    }

    public Division updateById(long id, DivisionDto division) {
        Division existing = divisionRepository.findById(id).get();
        existing.setName(division.getName());
        return divisionRepository.save(existing);
    }
}
