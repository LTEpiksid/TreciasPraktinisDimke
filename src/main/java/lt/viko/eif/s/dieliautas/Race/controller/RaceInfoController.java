package lt.viko.eif.s.dieliautas.Race.controller;

import lt.viko.eif.s.dieliautas.Race.model.RaceInfo;
import lt.viko.eif.s.dieliautas.Race.modelassembler.RaceInfoAssembler;
import lt.viko.eif.s.dieliautas.Race.service.RaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Valdiklis, atsakingas už lenktynių informacijos operacijas.
 */
@RestController
@RequestMapping("/api/raceInfos")
public class RaceInfoController {

    @Autowired
    private RaceInfoService raceInfoService;

    @Autowired
    private RaceInfoAssembler assembler;

    /**
     * Grąžina visų lenktynių informacijos sąrašą.
     *
     * @return ResponseEntity su CollectionModel, kuriame yra visų lenktynių informacijos sąrašas.
     */
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<RaceInfo>>> getAllRaceInfos() {
        List<RaceInfo> raceInfos = raceInfoService.getAllRaceInfos();
        assembler.rel = "all";
        return ResponseEntity.ok(CollectionModel.of(assembler.toModelList(raceInfos)));
    }

    /**
     * Grąžina lenktynių informaciją pagal ID.
     *
     * @param id lenktynių informacijos ID
     * @return ResponseEntity su EntityModel, kuriame yra lenktynių informacija.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RaceInfo>> getRaceInfoById(@PathVariable int id) {
        RaceInfo raceInfo = raceInfoService.getRaceInfoById(id);
        assembler.rel = "findId";
        return ResponseEntity.ok(assembler.toModel(raceInfo));
    }

    /**
     * Sukuria naują lenktynių informaciją.
     *
     * @param raceInfo lenktynių informacijos objektas
     * @return ResponseEntity su EntityModel, kuriame yra sukurta lenktynių informacija.
     */
    @PostMapping
    public ResponseEntity<EntityModel<RaceInfo>> createRaceInfo(@RequestBody RaceInfo raceInfo) {
        RaceInfo newRaceInfo = raceInfoService.createRaceInfo(raceInfo);
        assembler.rel = "new";
        return ResponseEntity.ok(assembler.toModel(newRaceInfo));
    }

    /**
     * Atnaujina lenktynių informaciją pagal ID.
     *
     * @param id lenktynių informacijos ID
     * @param raceInfo lenktynių informacijos objektas
     * @return ResponseEntity su EntityModel, kuriame yra atnaujinta lenktynių informacija.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<RaceInfo>> updateRaceInfo(@PathVariable int id, @RequestBody RaceInfo raceInfo) {
        RaceInfo updatedRaceInfo = raceInfoService.updateRaceInfo(id, raceInfo);
        assembler.rel = "update";
        return ResponseEntity.ok(assembler.toModel(updatedRaceInfo));
    }

    /**
     * Ištrina lenktynių informaciją pagal ID.
     *
     * @param id lenktynių informacijos ID
     * @return ResponseEntity su NoContent statusu.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRaceInfo(@PathVariable int id) {
        raceInfoService.deleteRaceInfo(id);
        assembler.rel = "delete";
        return ResponseEntity.noContent().build();
    }
}
