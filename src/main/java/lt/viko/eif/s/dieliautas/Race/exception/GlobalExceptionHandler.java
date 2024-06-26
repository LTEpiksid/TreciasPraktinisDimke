package lt.viko.eif.s.dieliautas.Race.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Globalus išimčių tvarkytojas, skirtas tvarkyti įvairias išimtis visoje programoje.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Tvarko ResourceNotFoundException išimtis.
     *
     * @param ex išimtis
     * @param request užklausa
     * @return ResponseEntity su klaidos detalėmis ir NOT_FOUND statusu
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Tvarko BadRequestException išimtis.
     *
     * @param ex išimtis
     * @param request užklausa
     * @return ResponseEntity su klaidos detalėmis ir BAD_REQUEST statusu
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Tvarko visas kitas išimtis.
     *
     * @param ex išimtis
     * @param request užklausa
     * @return ResponseEntity su klaidos detalėmis ir INTERNAL_SERVER_ERROR statusu
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Vidinė klasė, skirta klaidos detalėms saugoti.
     */
    static class ErrorDetails {
        private String message;
        private String details;

        public ErrorDetails(String message, String details) {
            super();
            this.message = message;
            this.details = details;
        }

        public String getMessage() {
            return message;
        }

        public String getDetails() {
            return details;
        }
    }
}
