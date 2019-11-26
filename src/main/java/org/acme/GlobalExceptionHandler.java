package org.acme;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleLpRequestException(Exception ex) {
        return new ResponseEntity<>(new ExceptionResponseDTO("error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
