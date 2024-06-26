package lt.viko.eif.s.dieliautas.Race.service;

import lt.viko.eif.s.dieliautas.Race.model.RaceInfo;
import lt.viko.eif.s.dieliautas.Race.repository.RaceInfoRepository;
import lt.viko.eif.s.dieliautas.Race.repository.RaceRepository;
import lt.viko.eif.s.dieliautas.Race.repository.RacerRepository;
import lt.viko.eif.s.dieliautas.Race.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Paslauga, atsakinga už RaceInfo valdymą.
 */
@Service
public class RaceInfoService {

    @Autowired
    private RaceInfoRepository raceInfoRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private RacerRepository racerRepository;

    /**
     * Grąžina visų RaceInfo objektų sąrašą.
     *
     * @return visų RaceInfo objektų sąrašas
     */
    public List<RaceInfo> getAllRaceInfos() {
        return raceInfoRepository.findAll();
    }

    /**
     * Grąžina RaceInfo objektą pagal ID.
     *
     * @param id RaceInfo ID
     * @return RaceInfo objektas
     * @throws ResourceNotFoundException jei RaceInfo su nurodytu ID nerasta
     */
    public RaceInfo getRaceInfoById(int id) {
        return raceInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RaceInfo not found with id " + id));
    }

    /**
     * Sukuria naują RaceInfo objektą.
     *
     * @param raceInfo naujas RaceInfo objektas
     * @return sukurtas RaceInfo objektas
     */
    public RaceInfo createRaceInfo(RaceInfo raceInfo) {
        raceInfo.setRace(raceRepository.findById(raceInfo.getRace().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Race not found with id " + raceInfo.getRace().getId())));
        raceInfo.setRacer(racerRepository.findById(raceInfo.getRacer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Racer not found with id " + raceInfo.getRacer().getId())));
        return raceInfoRepository.save(raceInfo);
    }

    /**
     * Atnaujina esamą RaceInfo objektą.
     *
     * @param id RaceInfo ID
     * @param raceInfo atnaujintas RaceInfo objektas
     * @return atnaujintas RaceInfo objektas
     */
    public RaceInfo updateRaceInfo(int id, RaceInfo raceInfo) {
        RaceInfo existingRaceInfo = raceInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RaceInfo not found with id " + id));
        existingRaceInfo.setFinishTime(raceInfo.getFinishTime());
        existingRaceInfo.setPositionNumber(raceInfo.getPositionNumber());
        existingRaceInfo.setRace(raceRepository.findById(raceInfo.getRace().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Race not found with id " + raceInfo.getRace().getId())));
        existingRaceInfo.setRacer(racerRepository.findById(raceInfo.getRacer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Racer not found with id " + raceInfo.getRacer().getId())));
        return raceInfoRepository.save(existingRaceInfo);
    }

    /**
     * Ištrina RaceInfo objektą pagal ID.
     *
     * @param id RaceInfo ID
     * @throws ResourceNotFoundException jei RaceInfo su nurodytu ID nerasta
     */
    public void deleteRaceInfo(int id) {
        if (!raceInfoRepository.existsById(id)) {
            throw new ResourceNotFoundException("RaceInfo not found with id " + id);
        }
        raceInfoRepository.deleteById(id);
    }
}
