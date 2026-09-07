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
        log.info("GET /movement - Fetching all movements");
        List<Movement> movements = movementService.getAllMovements();
        log.debug("Found {} movements", movements.size());
        return ResponseEntity.ok(movements);
    }

    @GetMapping("{id}")
    public ResponseEntity<Movement> getMovementById(@PathVariable long id) {
        log.info("GET /movement/{} - Find movement by ID", id);
        Optional<Movement> movement = movementService.getMovementById(id);
        if (movement.isEmpty()) {
            log.warn("Movement with ID {} not found", id);
        } else {
            log.debug("Found movement: {}", movement.get().getName());
        }
        return movement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{query}")
    public ResponseEntity<List<Movement>> searchMovement(@PathVariable String query) {
        log.info("GET /movement/search/{} - Searching movements", query);
        List<Movement> results = movementService.searchMovement(query);
        log.debug("Found {} movements matching query: {}", results.size(), query);
        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity<Movement> createMovement(@RequestBody MovementDto dto) {
        log.info("POST /movement - Creating new movement: {}", dto.getName());
        try {
            Movement movement = movementService.findOrCreateMovement(dto);
            log.info("Successfully created/found movement with ID {}", movement.getId());
            return ResponseEntity.ok(movement);
        } catch (Exception e) {
            log.error("Error creating movement: {}", dto.getName(), e);
            throw e;
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteMovementById(@PathVariable long id) {
        log.info("DELETE /movement/{} - Deleting movement", id);
        Optional<Movement> movement = movementService.getMovementById(id);
        if(movement.isEmpty()) {
            log.warn("Movement with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
        try {
            movementService.deleteMovementById(id);
            log.info("Successfully deleted movement with ID {}", id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            log.error("Error deleting movement with ID {}", id, e);
            throw e;
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Movement> updateMovementById(@PathVariable long id, @RequestBody MovementDto dto) {
        log.info("PATCH /movement/{} - Updating movement: {}", id, dto.getName());
        Optional<Movement> movement = movementService.getMovementById(id);
        if(movement.isEmpty()) {
            log.warn("Movement with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
        try {
            Movement updatedMovement = movementService.updateMovement(id, dto);
            log.info("Successfully updated movement with ID {}", id);
            return ResponseEntity.ok(updatedMovement);
        } catch (Exception e) {
            log.error("Error updating movement with ID {}", id, e);
            throw e;
        }
    }
}
