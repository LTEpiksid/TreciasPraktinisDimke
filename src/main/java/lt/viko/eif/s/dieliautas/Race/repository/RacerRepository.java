package lt.viko.eif.s.dieliautas.Race.repository;

import lt.viko.eif.s.dieliautas.Race.model.Racer;
import lt.viko.eif.s.dieliautas.Race.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Saugo Racer objektus į duomenų bazę ir leidžia atlikti CRUD operacijas.
 */
public interface RacerRepository extends JpaRepository<Racer, Integer> {
    /**
     * Randa visus Racer objektus pagal Status.
     *
     * @param status Status objektas
     * @return Racer objektų sąrašas
     */
    List<Racer> findByStatus(Status status);
}
