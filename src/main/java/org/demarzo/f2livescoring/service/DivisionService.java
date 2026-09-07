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
        log.debug("getOrCreateByName - name={}", name);
        Division division = divisionRepository.findByName(name);
        if (division == null) {
            log.info("Division not found - creating new division: {}", name);
            division = new Division();
            division.setName(name);
            division = divisionRepository.save(division);
            log.info("Successfully created division with id {}", division.getId());
        } else {
            log.debug("Found existing division: {} (id={})", division.getName(), division.getId());
        }
        return division;
    }

    public Optional<Division> getById(long id) {
        log.debug("Finding Division by id {}", id);
        Optional<Division> division = divisionRepository.findById(id);
        if (division.isPresent()) {
            log.debug("Found division: {} (id={})", division.get().getName(), id);
        } else {
            log.debug("Division with id {} not found", id);
        }
        return division;
    }

    public void deleteById(long id) {
        log.debug("Deleting Division by id {}", id);
        Optional<Division> division = divisionRepository.findById(id);
        if (division.isPresent()) {
            divisionRepository.deleteById(id);
            log.info("Successfully deleted division: {} (id={})", division.get().getName(), id);
        } else {
            log.warn("Division with id {} not found, cannot delete", id);
        }
    }

    public List<Division> getAll() {
        log.debug("Fetching all divisions");
        List<Division> divisions = divisionRepository.findAll();
        log.debug("Found {} divisions", divisions.size());
        return divisions;
    }

    public Division updateById(long id, DivisionDto division) {
        log.debug("Updating Division id={} with name={}", id, division.getName());
        Optional<Division> existing = divisionRepository.findById(id);
        if (existing.isEmpty()) {
            log.error("Division with id {} not found, cannot update", id);
            throw new IllegalArgumentException("Division with id " + id + " not found");
        }
        Division div = existing.get();
        String oldName = div.getName();
        div.setName(division.getName());
        Division updated = divisionRepository.save(div);
        log.info("Successfully updated division - id={}, oldName={}, newName={}", id, oldName, division.getName());
        return updated;
    }
}
