package lt.viko.eif.s.dieliautas.Race.modelassembler;

import lt.viko.eif.s.dieliautas.Race.controller.RaceInfoController;
import lt.viko.eif.s.dieliautas.Race.model.RaceInfo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Komponentas, skirtas konvertuoti RaceInfo objektus į EntityModel objektus.
 */
@Component
public class RaceInfoAssembler implements RepresentationModelAssembler<RaceInfo, EntityModel<RaceInfo>> {

    public String rel;

    @Override
    public EntityModel<RaceInfo> toModel(RaceInfo raceInfo) {
        return EntityModel.of(raceInfo,
                !Objects.equals(rel, "all") ? linkTo(methodOn(RaceInfoController.class).getAllRaceInfos()).withRel("Get all")
                        : linkTo(methodOn(RaceInfoController.class).getAllRaceInfos()).withSelfRel(),
                !Objects.equals(rel, "findId") ? linkTo(methodOn(RaceInfoController.class).getRaceInfoById(raceInfo.getId())).withRel("Find by ID")
                        : linkTo(methodOn(RaceInfoController.class).getRaceInfoById(raceInfo.getId())).withSelfRel(),
                !Objects.equals(rel, "new") ? linkTo(methodOn(RaceInfoController.class).createRaceInfo(raceInfo)).withRel("Create RaceInfo")
                        : linkTo(methodOn(RaceInfoController.class).createRaceInfo(raceInfo)).withSelfRel(),
                !Objects.equals(rel, "update") ? linkTo(methodOn(RaceInfoController.class).updateRaceInfo(raceInfo.getId(), raceInfo)).withRel("Update RaceInfo")
                        : linkTo(methodOn(RaceInfoController.class).updateRaceInfo(raceInfo.getId(), raceInfo)).withSelfRel(),
                !Objects.equals(rel, "delete") ? linkTo(methodOn(RaceInfoController.class).deleteRaceInfo(raceInfo.getId())).withRel("Delete RaceInfo")
                        : linkTo(methodOn(RaceInfoController.class).deleteRaceInfo(raceInfo.getId())).withSelfRel()
        );
    }

    /**
     * Konvertuoja sąrašą RaceInfo objektų į EntityModel objektų sąrašą.
     *
     * @param raceInfos Lenktynių informacijos sąrašas
     * @return EntityModel objektų sąrašas
     */
    public List<EntityModel<RaceInfo>> toModelList(List<RaceInfo> raceInfos) {
        return raceInfos.stream().map(this::toModel).collect(Collectors.toList());
    }
}
