package org.example.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.example.model.basic.ErrorModel;
import org.example.utils.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class NotFoundAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorModel> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage(), e);
        var errorModel = new ErrorModel(SC_NOT_FOUND, e.getClass().getSimpleName(), e.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(errorModel);
    }

}
