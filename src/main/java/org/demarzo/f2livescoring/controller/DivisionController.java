package org.demarzo.f2livescoring.controller;

import lombok.extern.slf4j.Slf4j;
import org.demarzo.f2livescoring.dto.DivisionDto;
import org.demarzo.f2livescoring.model.Division;
import org.demarzo.f2livescoring.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("division")
public class DivisionController {
    @Autowired
    private DivisionService divisionService;

    @GetMapping
    public ResponseEntity<List<Division>> findAll() {
        log.info("GET /division - Fetching all divisions");
        List<Division> divisions = divisionService.getAll();
        log.debug("Found {} divisions", divisions.size());
        return ResponseEntity.ok(divisions);
    }

    @GetMapping("{id}")
    public ResponseEntity<Division> findById(@PathVariable long id) {
        log.info("GET /division/{} - Find division by ID", id);
        Optional<Division> division = divisionService.getById(id);
        if (division.isEmpty()) {
            log.warn("Division with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
        log.debug("Found division: {}", division.get().getName());
        return division.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        log.info("DELETE /division/{} - Deleting division", id);
        try {
            divisionService.deleteById(id);
            log.info("Successfully deleted division with ID {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error deleting division with ID {}", id, e);
            throw e;
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateById(@PathVariable long id, @RequestBody DivisionDto division) {
        log.info("PATCH /division/{} - Updating division with name: {}", id, division.getName());
        try {
            Division updatedDivision = divisionService.updateById(id, division);
            log.info("Successfully updated division with ID {}", id);
            return ResponseEntity.ok(updatedDivision);
        } catch (Exception e) {
            log.error("Error updating division with ID {}", id, e);
            throw e;
        }
    }
}
