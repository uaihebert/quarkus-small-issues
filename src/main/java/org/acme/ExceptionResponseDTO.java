package org.acme;

public class ExceptionResponseDTO extends RuntimeException {
    public ExceptionResponseDTO(String message) {
        super(message);
    }
}
