package org.demarzo.f2livescoring.handlers;

import lombok.extern.slf4j.Slf4j;
import org.demarzo.f2livescoring.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorDto handleGlobal(Exception exception) {
        log.error("Unhandled exception occurred - Type: {} - Message: {}", 
            exception.getClass().getName(), exception.getMessage(), exception);
        
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        ErrorDto errorDto = new ErrorDto(exception.getMessage(), exception.getClass().getName(), sw.toString());
        
        log.debug("Returning error response: {}", errorDto.getMessage());
        return errorDto;
    }
}
