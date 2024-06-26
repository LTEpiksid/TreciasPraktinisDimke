package lt.viko.eif.s.dieliautas.Race.exception;

/**
 * Išimtis, naudojama blogos užklausos atvejais.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
