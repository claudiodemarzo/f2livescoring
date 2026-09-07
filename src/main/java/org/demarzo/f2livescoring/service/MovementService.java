package org.demarzo.f2livescoring.service;

import lombok.extern.slf4j.Slf4j;
import org.demarzo.f2livescoring.dto.MovementDto;
import org.demarzo.f2livescoring.model.Movement;
import org.demarzo.f2livescoring.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovementService {

    @Autowired
    private MovementRepository movementRepository;

    public List<Movement> getAllMovements() {
        log.debug("Fetching all movements");
        List<Movement> movements = movementRepository.findAll();
        log.debug("Found {} movements", movements.size());
        return movements;
    }

    public Optional<Movement> getMovementById(long id) {
        log.debug("Finding Movement by id {}", id);
        Optional<Movement> movement = movementRepository.findById(id);
        if (movement.isPresent()) {
            log.debug("Found movement: {} (id={})", movement.get().getName(), id);
        } else {
            log.debug("Movement with id {} not found", id);
        }
        return movement;
    }

    public List<Movement> searchMovement(String query) {
        log.debug("Searching movements with query: {}", query);
        List<Movement> results = movementRepository.findMovementLooseMatchName(query);
        log.debug("Found {} movements matching query: {}", results.size(), query);
        return results;
    }

    public Movement findOrCreateMovement(MovementDto movementDto) {
        log.debug("Finding or creating movement: {}", movementDto.getName());
        Movement movement = movementRepository.findMovementByName(movementDto.getName());
        if (movement == null) {
            log.info("Movement not found - creating new movement: {}", movementDto.getName());
            movement = new Movement();
            movement.setName(movementDto.getName());
            movement = movementRepository.save(movement);
            log.info("Successfully created movement with id {}", movement.getId());
        } else {
            log.debug("Found existing movement: {} (id={})", movement.getName(), movement.getId());
        }
        return movement;
    }

    public void deleteMovementById(long id) {
        log.debug("Deleting Movement by id {}", id);
        Optional<Movement> movement = movementRepository.findById(id);
        if (movement.isPresent()) {
            movementRepository.deleteById(id);
            log.info("Successfully deleted movement: {} (id={})", movement.get().getName(), id);
        } else {
            log.warn("Movement with id {} not found, cannot delete", id);
        }
    }

    public Movement updateMovement(long id, MovementDto movementDto) {
        log.debug("Updating Movement id={} with name={}", id, movementDto.getName());
        Optional<Movement> optional = getMovementById(id);
        if (optional.isEmpty()) {
            log.error("Movement with id {} not found, cannot update", id);
            throw new IllegalArgumentException("Movement with id " + id + " not found");
        }
        Movement movement = optional.get();
        String oldName = movement.getName();
        movement.setName(movementDto.getName());
        Movement updated = movementRepository.save(movement);
        log.info("Successfully updated movement - id={}, oldName={}, newName={}", id, oldName, movementDto.getName());
        return updated;
    }
}
