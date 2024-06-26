package lt.viko.eif.s.dieliautas.Race.service;

import lt.viko.eif.s.dieliautas.Race.model.Status;
import lt.viko.eif.s.dieliautas.Race.repository.StatusRepository;
import lt.viko.eif.s.dieliautas.Race.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Paslauga, atsakinga už Status valdymą.
 */
@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    /**
     * Grąžina visų Status objektų sąrašą.
     *
     * @return visų Status objektų sąrašas
     */
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    /**
     * Grąžina Status objektą pagal ID.
     *
     * @param id Status ID
     * @return Status objektas
     * @throws ResourceNotFoundException jei Status su nurodytu ID nerasta
     */
    public Status getStatusById(int id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found with id " + id));
    }

    /**
     * Sukuria naują Status objektą.
     *
     * @param status naujas Status objektas
     * @return sukurtas Status objektas
     */
    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    /**
     * Atnaujina esamą Status objektą.
     *
     * @param id Status ID
     * @param status atnaujintas Status objektas
     * @return atnaujintas Status objektas
     */
    public Status updateStatus(int id, Status status) {
        Status existingStatus = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found with id " + id));
        existingStatus.setStatusName(status.getStatusName());
        return statusRepository.save(existingStatus);
    }

    /**
     * Ištrina Status objektą pagal ID.
     *
     * @param id Status ID
     * @throws ResourceNotFoundException jei Status su nurodytu ID nerasta
     */
    public void deleteStatus(int id) {
        if (!statusRepository.existsById(id)) {
            throw new ResourceNotFoundException("Status not found with id " + id);
        }
        statusRepository.deleteById(id);
    }
}
