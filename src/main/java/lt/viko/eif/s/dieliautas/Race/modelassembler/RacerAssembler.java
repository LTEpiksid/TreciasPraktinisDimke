package lt.viko.eif.s.dieliautas.Race.modelassembler;

import lt.viko.eif.s.dieliautas.Race.controller.RacerController;
import lt.viko.eif.s.dieliautas.Race.model.Racer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Komponentas, skirtas konvertuoti Racer objektus į EntityModel objektus.
 */
@Component
public class RacerAssembler implements RepresentationModelAssembler<Racer, EntityModel<Racer>> {

    public String rel;

    @Override
    public EntityModel<Racer> toModel(Racer racer) {
        return EntityModel.of(racer,
                !Objects.equals(rel, "all") ? linkTo(methodOn(RacerController.class).getAllRacers()).withRel("Get all")
                        : linkTo(methodOn(RacerController.class).getAllRacers()).withSelfRel(),
                !Objects.equals(rel, "findId") ? linkTo(methodOn(RacerController.class).getRacerById(racer.getId())).withRel("Find by ID")
                        : linkTo(methodOn(RacerController.class).getRacerById(racer.getId())).withSelfRel(),
                !Objects.equals(rel, "new") ? linkTo(methodOn(RacerController.class).createRacer(racer)).withRel("Create Racer")
                        : linkTo(methodOn(RacerController.class).createRacer(racer)).withSelfRel(),
                !Objects.equals(rel, "update") ? linkTo(methodOn(RacerController.class).updateRacer(racer.getId(), racer)).withRel("Update Racer")
                        : linkTo(methodOn(RacerController.class).updateRacer(racer.getId(), racer)).withSelfRel(),
                !Objects.equals(rel, "delete") ? linkTo(methodOn(RacerController.class).deleteRacer(racer.getId())).withRel("Delete Racer")
                        : linkTo(methodOn(RacerController.class).deleteRacer(racer.getId())).withSelfRel()
        );
    }

    /**
     * Konvertuoja sąrašą Racer objektų į EntityModel objektų sąrašą.
     *
     * @param racers Lenktynininkų sąrašas
     * @return EntityModel objektų sąrašas
     */
    public List<EntityModel<Racer>> toModelList(List<Racer> racers) {
        return racers.stream().map(this::toModel).collect(Collectors.toList());
    }
}
