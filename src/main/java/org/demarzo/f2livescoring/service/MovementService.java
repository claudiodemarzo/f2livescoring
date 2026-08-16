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
        return movementRepository.findAll();
    }

    public Optional<Movement> getMovementById(long id) {
        return movementRepository.findById(id);
    }

    public List<Movement> searchMovement(String query) {
        return movementRepository.findMovementLooseMatchName(query);
    }

    public Movement findOrCreateMovement(MovementDto movementDto) {
        Movement movement = movementRepository.findMovementByName(movementDto.getName());
        if (movement == null) {
            movement = new Movement();
            movement.setName(movementDto.getName());
            movement = movementRepository.save(movement);
        }
        return movement;
    }

    public void deleteMovementById(long id) {
        movementRepository.deleteById(id);
    }

    public Movement updateMovement(long id, MovementDto movementDto) {
        Movement movementToUpdate = getMovementById(id).get();
        movementToUpdate.setName(movementDto.getName());
        return movementRepository.save(movementToUpdate);
    }

}
