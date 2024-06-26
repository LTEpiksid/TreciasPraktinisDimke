package lt.viko.eif.s.dieliautas.Race.service;

import lt.viko.eif.s.dieliautas.Race.model.Racer;
import lt.viko.eif.s.dieliautas.Race.model.Status;
import lt.viko.eif.s.dieliautas.Race.repository.RacerRepository;
import lt.viko.eif.s.dieliautas.Race.repository.StatusRepository;
import lt.viko.eif.s.dieliautas.Race.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Paslauga, atsakinga už Racer valdymą.
 */
@Service
public class RacerService {

    @Autowired
    private RacerRepository racerRepository;

    @Autowired
    private StatusRepository statusRepository;

    /**
     * Grąžina visų Racer objektų sąrašą.
     *
     * @return visų Racer objektų sąrašas
     */
    public List<Racer> getAllRacers() {
        return racerRepository.findAll();
    }

    /**
     * Grąžina Racer objektą pagal ID.
     *
     * @param id Racer ID
     * @return Racer objektas
     * @throws ResourceNotFoundException jei Racer su nurodytu ID nerasta
     */
    public Racer getRacerById(int id) {
        return racerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Racer not found with id " + id));
    }

    /**
     * Sukuria naują Racer objektą.
     *
     * @param racer naujas Racer objektas
     * @return sukurtas Racer objektas
     */
    public Racer createRacer(Racer racer) {
        racer.setStatus(statusRepository.findById(racer.getStatus().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Status not found with id " + racer.getStatus().getId())));
        return racerRepository.save(racer);
    }

    /**
     * Atnaujina esamą Racer objektą.
     *
     * @param id Racer ID
     * @param racer atnaujintas Racer objektas
     * @return atnaujintas Racer objektas
     */
    public Racer updateRacer(int id, Racer racer) {
        Racer existingRacer = racerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Racer not found with id " + id));
        existingRacer.setFirstName(racer.getFirstName());
        existingRacer.setLastName(racer.getLastName());
        existingRacer.setPhoneNumber(racer.getPhoneNumber());
        existingRacer.setStatus(statusRepository.findById(racer.getStatus().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Status not found with id " + racer.getStatus().getId())));
        return racerRepository.save(existingRacer);
    }

    /**
     * Ištrina Racer objektą pagal ID.
     *
     * @param id Racer ID
     * @throws ResourceNotFoundException jei Racer su nurodytu ID nerasta
     */
    public void deleteRacer(int id) {
        if (!racerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Racer not found with id " + id);
        }
        racerRepository.deleteById(id);
    }

    /**
     * Grąžina Racer objektų sąrašą pagal Status ID.
     *
     * @param statusId Status ID
     * @return Racer objektų sąrašas
     * @throws ResourceNotFoundException jei Status su nurodytu ID nerasta
     */
    public List<Racer> getRacersByStatusId(int statusId) {
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found with id " + statusId));
        return racerRepository.findByStatus(status);
    }
}
