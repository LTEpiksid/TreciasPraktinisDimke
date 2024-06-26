package lt.viko.eif.s.dieliautas.Race.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;

/**
 * Lenktynių informacijos klasė, atspindinti informaciją apie lenktynių rezultatus.
 */
@Entity
@Table(name = "raceinfo")
public class RaceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Time finishTime;
    private int positionNumber;

    @ManyToOne
    @JoinColumn(name = "race_id")
    @JsonIgnore
    private Race race;

    @ManyToOne
    @JoinColumn(name = "racer_id")
    @JsonIgnore
    private Racer racer;

    // Getter'iai ir setter'iai

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Time finishTime) {
        this.finishTime = finishTime;
    }

    public int getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(int positionNumber) {
        this.positionNumber = positionNumber;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }
}
