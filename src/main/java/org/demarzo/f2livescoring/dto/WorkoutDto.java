package org.demarzo.f2livescoring.dto;

import lombok.Data;

import java.util.List;

@Data
public class WorkoutDto {
    private String divisionId;
    private String divisionName;
    private List<WorkoutBlockDto> blocks;
}
