package lt.viko.eif.s.dieliautas.Race.repository;

import lt.viko.eif.s.dieliautas.Race.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Saugo Race objektus į duomenų bazę ir leidžia atlikti CRUD operacijas.
 */
public interface RaceRepository extends JpaRepository<Race, Integer> {
}
