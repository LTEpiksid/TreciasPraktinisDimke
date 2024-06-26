package lt.viko.eif.s.dieliautas.Race.controller;

import lt.viko.eif.s.dieliautas.Race.model.Status;
import lt.viko.eif.s.dieliautas.Race.modelassembler.StatusAssembler;
import lt.viko.eif.s.dieliautas.Race.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Valdiklis, atsakingas už statusų operacijas.
 */
@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @Autowired
    private StatusAssembler assembler;

    /**
     * Grąžina visų statusų sąrašą.
     *
     * @return ResponseEntity su CollectionModel, kuriame yra visų statusų sąrašas.
     */
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Status>>> getAllStatuses() {
        List<Status> statuses = statusService.getAllStatuses();
        assembler.rel = "all";
        return ResponseEntity.ok(CollectionModel.of(assembler.toModelList(statuses)));
    }

    /**
     * Grąžina statusą pagal ID.
     *
     * @param id statuso ID
     * @return ResponseEntity su EntityModel, kuriame yra statusas.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Status>> getStatusById(@PathVariable int id) {
        Status status = statusService.getStatusById(id);
        assembler.rel = "findId";
        return ResponseEntity.ok(assembler.toModel(status));
    }

    /**
     * Sukuria naują statusą.
     *
     * @param status statuso objektas
     * @return ResponseEntity su EntityModel, kuriame yra sukurtas statusas.
     */
    @PostMapping
    public ResponseEntity<EntityModel<Status>> createStatus(@RequestBody Status status) {
        Status newStatus = statusService.createStatus(status);
        assembler.rel = "new";
        return ResponseEntity.ok(assembler.toModel(newStatus));
    }

    /**
     * Atnaujina statusą pagal ID.
     *
     * @param id statuso ID
     * @param status statuso objektas
     * @return ResponseEntity su EntityModel, kuriame yra atnaujintas statusas.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Status>> updateStatus(@PathVariable int id, @RequestBody Status status) {
        Status updatedStatus = statusService.updateStatus(id, status);
        assembler.rel = "update";
        return ResponseEntity.ok(assembler.toModel(updatedStatus));
    }

    /**
     * Ištrina statusą pagal ID.
     *
     * @param id statuso ID
     * @return ResponseEntity su NoContent statusu.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable int id) {
        statusService.deleteStatus(id);
        assembler.rel = "delete";
        return ResponseEntity.noContent().build();
    }
}
