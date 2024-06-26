package lt.viko.eif.s.dieliautas.Race.service;

import lt.viko.eif.s.dieliautas.Race.model.Race;
import lt.viko.eif.s.dieliautas.Race.repository.RaceRepository;
import lt.viko.eif.s.dieliautas.Race.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Paslauga, atsakinga už Race valdymą.
 */
@Service
public class RaceService {

    @Autowired
    private RaceRepository raceRepository;

    /**
     * Grąžina visų Race objektų sąrašą.
     *
     * @return visų Race objektų sąrašas
     */
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    /**
     * Grąžina Race objektą pagal ID.
     *
     * @param id Race ID
     * @return Race objektas
     * @throws ResourceNotFoundException jei Race su nurodytu ID nerasta
     */
    public Race getRaceById(int id) {
        return raceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Race not found with id " + id));
    }

    /**
     * Sukuria naują Race objektą.
     *
     * @param race naujas Race objektas
     * @return sukurtas Race objektas
     */
    public Race createRace(Race race) {
        return raceRepository.save(race);
    }

    /**
     * Atnaujina esamą Race objektą.
     *
     * @param id Race ID
     * @param race atnaujintas Race objektas
     * @return atnaujintas Race objektas
     */
    public Race updateRace(int id, Race race) {
        Race existingRace = raceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Race not found with id " + id));
        existingRace.setName(race.getName());
        existingRace.setLocation(race.getLocation());
        existingRace.setDate(race.getDate());
        return raceRepository.save(existingRace);
    }

    /**
     * Ištrina Race objektą pagal ID.
     *
     * @param id Race ID
     * @throws ResourceNotFoundException jei Race su nurodytu ID nerasta
     */
    public void deleteRace(int id) {
        if (!raceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Race not found with id " + id);
        }
        raceRepository.deleteById(id);
    }

    /**
     * Grąžina Race objektų sąrašą pagal nurodytą datų intervalą.
     *
     * @param startDate pradžios data
     * @param endDate pabaigos data
     * @return Race objektų sąrašas pagal datų intervalą
     */
    public List<Race> getRacesByDateRange(Date startDate, Date endDate) {
        return raceRepository.findAll().stream()
                .filter(race -> !race.getDate().before(startDate) && !race.getDate().after(endDate))
                .collect(Collectors.toList());
    }
}
