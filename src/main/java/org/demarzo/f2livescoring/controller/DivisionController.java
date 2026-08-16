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
        return ResponseEntity.ok(divisionService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Division> findById(@PathVariable long id) {
        Optional<Division> division = divisionService.getById(id);
        return division.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        divisionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateById(@PathVariable long id, @RequestBody DivisionDto division) {
        Division updatedDivision = divisionService.updateById(id, division);
        return ResponseEntity.ok(updatedDivision);
    }
}
