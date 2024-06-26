package lt.viko.eif.s.dieliautas.Race.controller;

import lt.viko.eif.s.dieliautas.Race.model.Racer;
import lt.viko.eif.s.dieliautas.Race.modelassembler.RacerAssembler;
import lt.viko.eif.s.dieliautas.Race.service.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/racers")
public class RacerController {

    @Autowired
    private RacerService racerService;

    @Autowired
    private RacerAssembler assembler;

    /**
     * Gauti visus lenktynininkus.
     *
     * @return Visų lenktynininkų kolekcijos modelis.
     */
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Racer>>> getAllRacers() {
        List<Racer> racers = racerService.getAllRacers();
        assembler.rel = "all";
        return ResponseEntity.ok(CollectionModel.of(assembler.toModelList(racers)));
    }

    /**
     * Gauti lenktynininką pagal ID.
     *
     * @param id Lenktynininko ID.
     * @return Lenktynininko modelis.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Racer>> getRacerById(@PathVariable int id) {
        Racer racer = racerService.getRacerById(id);
        assembler.rel = "findId";
        return ResponseEntity.ok(assembler.toModel(racer));
    }

    /**
     * Sukurti naują lenktynininką.
     *
     * @param racer Lenktynininko duomenys.
     * @return Sukurto lenktynininko modelis.
     */
    @PostMapping
    public ResponseEntity<EntityModel<Racer>> createRacer(@RequestBody Racer racer) {
        Racer newRacer = racerService.createRacer(racer);
        assembler.rel = "new";
        return ResponseEntity.ok(assembler.toModel(newRacer));
    }

    /**
     * Atnaujinti esamą lenktynininką.
     *
     * @param id Lenktynininko ID.
     * @param racer Atnaujinti lenktynininko duomenys.
     * @return Atnaujinto lenktynininko modelis.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Racer>> updateRacer(@PathVariable int id, @RequestBody Racer racer) {
        Racer updatedRacer = racerService.updateRacer(id, racer);
        assembler.rel = "update";
        return ResponseEntity.ok(assembler.toModel(updatedRacer));
    }

    /**
     * Ištrinti lenktynininką pagal ID.
     *
     * @param id Lenktynininko ID.
     * @return Tuščias atsakymas.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRacer(@PathVariable int id) {
        racerService.deleteRacer(id);
        assembler.rel = "delete";
        return ResponseEntity.noContent().build();
    }

    /**
     * Gauti lenktynininkus pagal statuso ID.
     *
     * @param statusId Statuso ID.
     * @return Lenktynininkų pagal statusą kolekcijos modelis.
     */
    @GetMapping("/status/{statusId}")
    public ResponseEntity<CollectionModel<EntityModel<Racer>>> getRacersByStatusId(@PathVariable int statusId) {
        List<Racer> racers = racerService.getRacersByStatusId(statusId);
        assembler.rel = "findByStatus";
        return ResponseEntity.ok(CollectionModel.of(assembler.toModelList(racers)));
    }
}
