package lt.viko.eif.s.dieliautas.Race.repository;

import lt.viko.eif.s.dieliautas.Race.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Saugo Status objektus į duomenų bazę ir leidžia atlikti CRUD operacijas.
 */
public interface StatusRepository extends JpaRepository<Status, Integer> {
}
