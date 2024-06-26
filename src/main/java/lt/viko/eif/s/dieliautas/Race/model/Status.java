package lt.viko.eif.s.dieliautas.Race.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Statuso klasė, atspindinti lenktynininko statusą.
 */
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String statusName;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<Racer> racers;

    // Getter'iai ir setter'iai

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Racer> getRacers() {
        return racers;
    }

    public void setRacers(List<Racer> racers) {
        this.racers = racers;
    }
}
