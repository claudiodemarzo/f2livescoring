package org.demarzo.f2livescoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String message;
    private String exceptionType;
    private String stackTrace;
}
