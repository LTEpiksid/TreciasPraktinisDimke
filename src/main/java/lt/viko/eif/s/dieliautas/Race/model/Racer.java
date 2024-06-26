package lt.viko.eif.s.dieliautas.Race.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Lenktynininko klasė, atspindinti lenktynininko informaciją.
 */
@Entity
public class Racer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @JsonIgnore
    private Status status;

    @OneToMany(mappedBy = "racer")
    @JsonIgnore
    private List<RaceInfo> raceInfos;

    // Getter'iai ir setter'iai

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<RaceInfo> getRaceInfos() {
        return raceInfos;
    }

    public void setRaceInfos(List<RaceInfo> raceInfos) {
        this.raceInfos = raceInfos;
    }
}
