package lt.viko.eif.s.dieliautas.Race.repository;

import lt.viko.eif.s.dieliautas.Race.model.RaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Saugo RaceInfo objektus į duomenų bazę ir leidžia atlikti CRUD operacijas.
 */
public interface RaceInfoRepository extends JpaRepository<RaceInfo, Integer> {
}
