package lt.viko.eif.s.dieliautas.Race.exception;

/**
 * Išimtis, naudojama resursui neradus.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
