package org.demarzo.f2livescoring.dto;

import lombok.Data;
import org.demarzo.f2livescoring.model.WorkoutBlockType;

import java.time.Duration;
import java.util.List;

@Data
public class WorkoutBlockDto {
    private WorkoutBlockType blockType;
    private List<WorkoutMovementDto> movements;
    private int rounds;
    private Duration timeCap;
}
