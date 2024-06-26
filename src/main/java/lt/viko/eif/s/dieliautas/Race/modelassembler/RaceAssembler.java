package lt.viko.eif.s.dieliautas.Race.modelassembler;

import lt.viko.eif.s.dieliautas.Race.controller.RaceController;
import lt.viko.eif.s.dieliautas.Race.model.Race;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Komponentas, skirtas konvertuoti Race objektus į EntityModel objektus.
 */
@Component
public class RaceAssembler implements RepresentationModelAssembler<Race, EntityModel<Race>> {

    public String rel;

    @Override
    public EntityModel<Race> toModel(Race race) {
        return EntityModel.of(race,
                !Objects.equals(rel, "all") ? linkTo(methodOn(RaceController.class).getAllRaces()).withRel("Get all")
                        : linkTo(methodOn(RaceController.class).getAllRaces()).withSelfRel(),
                !Objects.equals(rel, "findId") ? linkTo(methodOn(RaceController.class).getRaceById(race.getId())).withRel("Find by ID")
                        : linkTo(methodOn(RaceController.class).getRaceById(race.getId())).withSelfRel(),
                !Objects.equals(rel, "new") ? linkTo(methodOn(RaceController.class).createRace(race)).withRel("Create Race")
                        : linkTo(methodOn(RaceController.class).createRace(race)).withSelfRel(),
                !Objects.equals(rel, "update") ? linkTo(methodOn(RaceController.class).updateRace(race.getId(), race)).withRel("Update Race")
                        : linkTo(methodOn(RaceController.class).updateRace(race.getId(), race)).withSelfRel(),
                !Objects.equals(rel, "delete") ? linkTo(methodOn(RaceController.class).deleteRace(race.getId())).withRel("Delete Race")
                        : linkTo(methodOn(RaceController.class).deleteRace(race.getId())).withSelfRel()
        );
    }

    /**
     * Konvertuoja sąrašą Race objektų į EntityModel objektų sąrašą.
     *
     * @param races Lenktynių sąrašas
     * @return EntityModel objektų sąrašas
     */
    public List<EntityModel<Race>> toModelList(List<Race> races) {
        return races.stream().map(this::toModel).collect(Collectors.toList());
    }
}
