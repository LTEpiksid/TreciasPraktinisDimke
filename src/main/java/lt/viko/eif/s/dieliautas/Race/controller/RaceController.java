package lt.viko.eif.s.dieliautas.Race.controller;

import lt.viko.eif.s.dieliautas.Race.model.Race;
import lt.viko.eif.s.dieliautas.Race.modelassembler.RaceAssembler;
import lt.viko.eif.s.dieliautas.Race.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/races")
public class RaceController {

    @Autowired
    private RaceService raceService;

    @Autowired
    private RaceAssembler assembler;

    /**
     * Gauti visas lenktynes.
     *
     * @return Visų lenktynių kolekcijos modelis.
     */
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Race>>> getAllRaces() {
        List<Race> races = raceService.getAllRaces();
        assembler.rel = "all";
        return ResponseEntity.ok(CollectionModel.of(assembler.toModelList(races)));
    }

    /**
     * Gauti lenktynes pagal ID.
     *
     * @param id Lenktynių ID.
     * @return Lenktynių modelis.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Race>> getRaceById(@PathVariable int id) {
        Race race = raceService.getRaceById(id);
        assembler.rel = "findId";
        return ResponseEntity.ok(assembler.toModel(race));
    }

    /**
     * Sukurti naujas lenktynes.
     *
     * @param race Lenktynių duomenys.
     * @return Sukurtų lenktynių modelis.
     */
    @PostMapping
    public ResponseEntity<EntityModel<Race>> createRace(@RequestBody Race race) {
        Race newRace = raceService.createRace(race);
        assembler.rel = "new";
        return ResponseEntity.ok(assembler.toModel(newRace));
    }

    /**
     * Atnaujinti esamas lenktynes.
     *
     * @param id Lenktynių ID.
     * @param race Atnaujinti lenktynių duomenys.
     * @return Atnaujintų lenktynių modelis.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Race>> updateRace(@PathVariable int id, @RequestBody Race race) {
        Race updatedRace = raceService.updateRace(id, race);
        assembler.rel = "update";
        return ResponseEntity.ok(assembler.toModel(updatedRace));
    }

    /**
     * Ištrinti lenktynes pagal ID.
     *
     * @param id Lenktynių ID.
     * @return Tuščias atsakymas.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRace(@PathVariable int id) {
        raceService.deleteRace(id);
        assembler.rel = "delete";
        return ResponseEntity.noContent().build();
    }

    /**
     * Gauti lenktynes pagal datų intervalą.
     *
     * @param startDate Pradžios data.
     * @param endDate Pabaigos data.
     * @return Lenktynių pagal datų intervalą kolekcijos modelis.
     */
    @GetMapping("/daterange")
    public ResponseEntity<CollectionModel<EntityModel<Race>>> getRacesByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<Race> races = raceService.getRacesByDateRange(startDate, endDate);
        assembler.rel = "findByDateRange";
        return ResponseEntity.ok(CollectionModel.of(assembler.toModelList(races)));
    }
}
