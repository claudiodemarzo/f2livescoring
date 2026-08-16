package org.demarzo.f2livescoring.controller;

import lombok.extern.slf4j.Slf4j;
import org.demarzo.f2livescoring.dto.MovementDto;
import org.demarzo.f2livescoring.model.Movement;
import org.demarzo.f2livescoring.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movement")
@Slf4j
public class MovementController {

    @Autowired
    private MovementService movementService;

    @GetMapping
    public ResponseEntity<List<Movement>> getAllMovements() {
        return ResponseEntity.ok(movementService.getAllMovements());
    }

    @GetMapping("{id}")
    public ResponseEntity<Movement> getMovementById(@PathVariable long id) {
        Optional<Movement> movement = movementService.getMovementById(id);

        return movement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{query}")
    public ResponseEntity<List<Movement>> searchMovement(@PathVariable String query) {
        return ResponseEntity.ok(movementService.searchMovement(query));
    }

    @PostMapping
    public ResponseEntity<Movement> createMovement(@RequestBody MovementDto dto) {
        Movement movement = movementService.findOrCreateMovement(dto);
        return ResponseEntity.ok(movement);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteMovementById(@PathVariable long id) {
        Optional<Movement> movement = movementService.getMovementById(id);

        if(movement.isEmpty()) return ResponseEntity.notFound().build();
        movementService.deleteMovementById(id);
        return ResponseEntity.ok(true);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Movement> updateMovementById(@PathVariable long id, @RequestBody MovementDto dto) {
        Optional<Movement> movement = movementService.getMovementById(id);
        if(movement.isEmpty()) return ResponseEntity.notFound().build();
        Movement updatedMovement = movementService.updateMovement(id, dto);
        return ResponseEntity.ok(updatedMovement);
    }
}
