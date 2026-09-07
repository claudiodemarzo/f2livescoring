package org.demarzo.f2livescoring.dto;

import lombok.Data;
import org.demarzo.f2livescoring.model.Unit;

@Data
public class WorkoutMovementDto {
    private long movementId;
    private int reps;
    private int volume;
    private Unit unit;
}
