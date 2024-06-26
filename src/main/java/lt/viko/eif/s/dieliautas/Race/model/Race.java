package lt.viko.eif.s.dieliautas.Race.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Lenktynių klasė, atspindinti lenktynių informaciją.
 */
@Entity
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    private Date date;

    @OneToMany(mappedBy = "race")
    @JsonIgnore
    private List<RaceInfo> raceInfos;

    // Getter'iai ir setter'iai

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<RaceInfo> getRaceInfos() {
        return raceInfos;
    }

    public void setRaceInfos(List<RaceInfo> raceInfos) {
        this.raceInfos = raceInfos;
    }
}
