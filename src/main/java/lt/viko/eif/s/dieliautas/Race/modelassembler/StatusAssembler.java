package lt.viko.eif.s.dieliautas.Race.modelassembler;

import lt.viko.eif.s.dieliautas.Race.controller.StatusController;
import lt.viko.eif.s.dieliautas.Race.model.Status;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Komponentas, skirtas konvertuoti Status objektus į EntityModel objektus.
 */
@Component
public class StatusAssembler implements RepresentationModelAssembler<Status, EntityModel<Status>> {

    public String rel;

    @Override
    public EntityModel<Status> toModel(Status status) {
        return EntityModel.of(status,
                !Objects.equals(rel, "all") ? linkTo(methodOn(StatusController.class).getAllStatuses()).withRel("Get all")
                        : linkTo(methodOn(StatusController.class).getAllStatuses()).withSelfRel(),
                !Objects.equals(rel, "findId") ? linkTo(methodOn(StatusController.class).getStatusById(status.getId())).withRel("Find by ID")
                        : linkTo(methodOn(StatusController.class).getStatusById(status.getId())).withSelfRel(),
                !Objects.equals(rel, "new") ? linkTo(methodOn(StatusController.class).createStatus(status)).withRel("Create Status")
                        : linkTo(methodOn(StatusController.class).createStatus(status)).withSelfRel(),
                !Objects.equals(rel, "update") ? linkTo(methodOn(StatusController.class).updateStatus(status.getId(), status)).withRel("Update Status")
                        : linkTo(methodOn(StatusController.class).updateStatus(status.getId(), status)).withSelfRel(),
                !Objects.equals(rel, "delete") ? linkTo(methodOn(StatusController.class).deleteStatus(status.getId())).withRel("Delete Status")
                        : linkTo(methodOn(StatusController.class).deleteStatus(status.getId())).withSelfRel()
        );
    }

    /**
     * Konvertuoja sąrašą Status objektų į EntityModel objektų sąrašą.
     *
     * @param statuses Statusų sąrašas
     * @return EntityModel objektų sąrašas
     */
    public List<EntityModel<Status>> toModelList(List<Status> statuses) {
        return statuses.stream().map(this::toModel).collect(Collectors.toList());
    }
}
