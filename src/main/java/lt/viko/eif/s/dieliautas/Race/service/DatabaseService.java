package lt.viko.eif.s.dieliautas.Race.service;

import lt.viko.eif.s.dieliautas.Race.model.*;
import lt.viko.eif.s.dieliautas.Race.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.sql.Time;

/**
 * Ši paslauga yra atsakinga už duomenų bazės pradinį užpildymą duomenimis.
 */
@Service
public class DatabaseService {

    @PersistenceContext
    private EntityManager entityManager;

    private final RaceRepository raceRepository;
    private final StatusRepository statusRepository;
    private final RacerRepository racerRepository;
    private final RaceInfoRepository raceInfoRepository;

    @Autowired
    public DatabaseService(RaceRepository raceRepository, StatusRepository statusRepository,
                           RacerRepository racerRepository, RaceInfoRepository raceInfoRepository) {
        this.raceRepository = raceRepository;
        this.statusRepository = statusRepository;
        this.racerRepository = racerRepository;
        this.raceInfoRepository = raceInfoRepository;
    }

    /**
     * Šis metodas ištrina visus esamus duomenis iš duomenų bazės ir įterpia naujus pradinius duomenis.
     */
    @Transactional
    public void initializeDatabase() {
        // Ištrina visus esamus duomenis
        raceInfoRepository.deleteAllInBatch();
        racerRepository.deleteAllInBatch();
        raceRepository.deleteAllInBatch();
        statusRepository.deleteAllInBatch();

        // Nustato auto-increment reikšmes naudojant Hibernate
        resetAutoIncrement("raceinfo");
        resetAutoIncrement("racer");
        resetAutoIncrement("race");
        resetAutoIncrement("status");

        // Inicijuoja statusus
        Status activeStatus = new Status();
        activeStatus.setStatusName("Active");
        Status retiredStatus = new Status();
        retiredStatus.setStatusName("Retired");
        statusRepository.save(activeStatus);
        statusRepository.save(retiredStatus);

        // Inicijuoja lenktynes
        Race race1 = new Race();
        race1.setName("Spring Marathon");
        race1.setLocation("New York");
        race1.setDate(Date.valueOf("2023-05-28"));
        raceRepository.save(race1);

        Race race2 = new Race();
        race2.setName("Summer Sprint");
        race2.setLocation("Los Angeles");
        race2.setDate(Date.valueOf("2023-06-15"));
        raceRepository.save(race2);

        Race race3 = new Race();
        race3.setName("Autumn Run");
        race3.setLocation("Chicago");
        race3.setDate(Date.valueOf("2023-07-20"));
        raceRepository.save(race3);

        Race race4 = new Race();
        race4.setName("Winter Dash");
        race4.setLocation("Houston");
        race4.setDate(Date.valueOf("2023-08-25"));
        raceRepository.save(race4);

        Race race5 = new Race();
        race5.setName("Desert Rally");
        race5.setLocation("Phoenix");
        race5.setDate(Date.valueOf("2023-09-30"));
        raceRepository.save(race5);

        Race race6 = new Race();
        race6.setName("Mountain Climb");
        race6.setLocation("Denver");
        race6.setDate(Date.valueOf("2023-10-05"));
        raceRepository.save(race6);

        Race race7 = new Race();
        race7.setName("City Loop");
        race7.setLocation("San Francisco");
        race7.setDate(Date.valueOf("2023-11-11"));
        raceRepository.save(race7);

        Race race8 = new Race();
        race8.setName("Beachside Race");
        race8.setLocation("Miami");
        race8.setDate(Date.valueOf("2023-12-20"));
        raceRepository.save(race8);

        Race race9 = new Race();
        race9.setName("Forest Trail");
        race9.setLocation("Portland");
        race9.setDate(Date.valueOf("2024-01-15"));
        raceRepository.save(race9);

        Race race10 = new Race();
        race10.setName("Urban Challenge");
        race10.setLocation("Seattle");
        race10.setDate(Date.valueOf("2024-02-18"));
        raceRepository.save(race10);

        // Inicijuoja lenktynininkus
        Racer racer1 = new Racer();
        racer1.setFirstName("John");
        racer1.setLastName("Doe");
        racer1.setPhoneNumber("1234567890");
        racer1.setStatus(activeStatus);
        racerRepository.save(racer1);

        Racer racer2 = new Racer();
        racer2.setFirstName("Jane");
        racer2.setLastName("Smith");
        racer2.setPhoneNumber("0987654321");
        racer2.setStatus(activeStatus);
        racerRepository.save(racer2);

        Racer racer3 = new Racer();
        racer3.setFirstName("Jim");
        racer3.setLastName("Beam");
        racer3.setPhoneNumber("5555555555");
        racer3.setStatus(activeStatus);
        racerRepository.save(racer3);

        Racer racer4 = new Racer();
        racer4.setFirstName("Jack");
        racer4.setLastName("Daniels");
        racer4.setPhoneNumber("4444444444");
        racer4.setStatus(retiredStatus);
        racerRepository.save(racer4);

        Racer racer5 = new Racer();
        racer5.setFirstName("Jose");
        racer5.setLastName("Cuervo");
        racer5.setPhoneNumber("3333333333");
        racer5.setStatus(retiredStatus);
        racerRepository.save(racer5);

        Racer racer6 = new Racer();
        racer6.setFirstName("Jill");
        racer6.setLastName("Valentine");
        racer6.setPhoneNumber("2222222222");
        racer6.setStatus(activeStatus);
        racerRepository.save(racer6);

        Racer racer7 = new Racer();
        racer7.setFirstName("Chris");
        racer7.setLastName("Redfield");
        racer7.setPhoneNumber("1111111111");
        racer7.setStatus(activeStatus);
        racerRepository.save(racer7);

        Racer racer8 = new Racer();
        racer8.setFirstName("Leon");
        racer8.setLastName("Kennedy");
        racer8.setPhoneNumber("6666666666");
        racer8.setStatus(retiredStatus);
        racerRepository.save(racer8);

        Racer racer9 = new Racer();
        racer9.setFirstName("Claire");
        racer9.setLastName("Redfield");
        racer9.setPhoneNumber("7777777777");
        racer9.setStatus(activeStatus);
        racerRepository.save(racer9);

        Racer racer10 = new Racer();
        racer10.setFirstName("Ada");
        racer10.setLastName("Wong");
        racer10.setPhoneNumber("8888888888");
        racer10.setStatus(activeStatus);
        racerRepository.save(racer10);

        // Inicijuoja lenktynių informacijas
        RaceInfo raceInfo1 = new RaceInfo();
        raceInfo1.setRace(race1);
        raceInfo1.setRacer(racer1);
        raceInfo1.setFinishTime(Time.valueOf("01:30:00"));
        raceInfo1.setPositionNumber(1);
        raceInfoRepository.save(raceInfo1);

        RaceInfo raceInfo2 = new RaceInfo();
        raceInfo2.setRace(race1);
        raceInfo2.setRacer(racer2);
        raceInfo2.setFinishTime(Time.valueOf("01:35:00"));
        raceInfo2.setPositionNumber(2);
        raceInfoRepository.save(raceInfo2);

        RaceInfo raceInfo3 = new RaceInfo();
        raceInfo3.setRace(race2);
        raceInfo3.setRacer(racer3);
        raceInfo3.setFinishTime(Time.valueOf("01:40:00"));
        raceInfo3.setPositionNumber(1);
        raceInfoRepository.save(raceInfo3);

        RaceInfo raceInfo4 = new RaceInfo();
        raceInfo4.setRace(race2);
        raceInfo4.setRacer(racer4);
        raceInfo4.setFinishTime(Time.valueOf("01:45:00"));
        raceInfo4.setPositionNumber(2);
        raceInfoRepository.save(raceInfo4);

        RaceInfo raceInfo5 = new RaceInfo();
        raceInfo5.setRace(race3);
        raceInfo5.setRacer(racer5);
        raceInfo5.setFinishTime(Time.valueOf("01:50:00"));
        raceInfo5.setPositionNumber(1);
        raceInfoRepository.save(raceInfo5);

        RaceInfo raceInfo6 = new RaceInfo();
        raceInfo6.setRace(race3);
        raceInfo6.setRacer(racer6);
        raceInfo6.setFinishTime(Time.valueOf("01:55:00"));
        raceInfo6.setPositionNumber(2);
        raceInfoRepository.save(raceInfo6);

        RaceInfo raceInfo7 = new RaceInfo();
        raceInfo7.setRace(race4);
        raceInfo7.setRacer(racer7);
        raceInfo7.setFinishTime(Time.valueOf("02:00:00"));
        raceInfo7.setPositionNumber(1);
        raceInfoRepository.save(raceInfo7);

        RaceInfo raceInfo8 = new RaceInfo();
        raceInfo8.setRace(race4);
        raceInfo8.setRacer(racer8);
        raceInfo8.setFinishTime(Time.valueOf("02:05:00"));
        raceInfo8.setPositionNumber(2);
        raceInfoRepository.save(raceInfo8);

        RaceInfo raceInfo9 = new RaceInfo();
        raceInfo9.setRace(race5);
        raceInfo9.setRacer(racer9);
        raceInfo9.setFinishTime(Time.valueOf("02:10:00"));
        raceInfo9.setPositionNumber(1);
        raceInfoRepository.save(raceInfo9);

        RaceInfo raceInfo10 = new RaceInfo();
        raceInfo10.setRace(race5);
        raceInfo10.setRacer(racer10);
        raceInfo10.setFinishTime(Time.valueOf("02:15:00"));
        raceInfo10.setPositionNumber(2);
        raceInfoRepository.save(raceInfo10);

        RaceInfo raceInfo11 = new RaceInfo();
        raceInfo11.setRace(race6);
        raceInfo11.setRacer(racer1);
        raceInfo11.setFinishTime(Time.valueOf("01:20:00"));
        raceInfo11.setPositionNumber(1);
        raceInfoRepository.save(raceInfo11);

        RaceInfo raceInfo12 = new RaceInfo();
        raceInfo12.setRace(race6);
        raceInfo12.setRacer(racer2);
        raceInfo12.setFinishTime(Time.valueOf("01:25:00"));
        raceInfo12.setPositionNumber(2);
        raceInfoRepository.save(raceInfo12);

        RaceInfo raceInfo13 = new RaceInfo();
        raceInfo13.setRace(race7);
        raceInfo13.setRacer(racer3);
        raceInfo13.setFinishTime(Time.valueOf("01:30:00"));
        raceInfo13.setPositionNumber(1);
        raceInfoRepository.save(raceInfo13);

        RaceInfo raceInfo14 = new RaceInfo();
        raceInfo14.setRace(race7);
        raceInfo14.setRacer(racer4);
        raceInfo14.setFinishTime(Time.valueOf("01:35:00"));
        raceInfo14.setPositionNumber(2);
        raceInfoRepository.save(raceInfo14);

        RaceInfo raceInfo15 = new RaceInfo();
        raceInfo15.setRace(race8);
        raceInfo15.setRacer(racer5);
        raceInfo15.setFinishTime(Time.valueOf("01:40:00"));
        raceInfo15.setPositionNumber(1);
        raceInfoRepository.save(raceInfo15);

        RaceInfo raceInfo16 = new RaceInfo();
        raceInfo16.setRace(race8);
        raceInfo16.setRacer(racer6);
        raceInfo16.setFinishTime(Time.valueOf("01:45:00"));
        raceInfo16.setPositionNumber(2);
        raceInfoRepository.save(raceInfo16);

        RaceInfo raceInfo17 = new RaceInfo();
        raceInfo17.setRace(race9);
        raceInfo17.setRacer(racer7);
        raceInfo17.setFinishTime(Time.valueOf("01:50:00"));
        raceInfo17.setPositionNumber(1);
        raceInfoRepository.save(raceInfo17);

        RaceInfo raceInfo18 = new RaceInfo();
        raceInfo18.setRace(race9);
        raceInfo18.setRacer(racer8);
        raceInfo18.setFinishTime(Time.valueOf("01:55:00"));
        raceInfo18.setPositionNumber(2);
        raceInfoRepository.save(raceInfo18);

        RaceInfo raceInfo19 = new RaceInfo();
        raceInfo19.setRace(race10);
        raceInfo19.setRacer(racer9);
        raceInfo19.setFinishTime(Time.valueOf("02:00:00"));
        raceInfo19.setPositionNumber(1);
        raceInfoRepository.save(raceInfo19);

        RaceInfo raceInfo20 = new RaceInfo();
        raceInfo20.setRace(race10);
        raceInfo20.setRacer(racer10);
        raceInfo20.setFinishTime(Time.valueOf("02:05:00"));
        raceInfo20.setPositionNumber(2);
        raceInfoRepository.save(raceInfo20);
    }

    /**
     * Nustato lentelės auto-increment reikšmę į 1.
     *
     * @param tableName lentelės pavadinimas
     */
    private void resetAutoIncrement(String tableName) {
        String resetSql = String.format("ALTER TABLE %s AUTO_INCREMENT = 1", tableName);
        entityManager.createNativeQuery(resetSql).executeUpdate();
    }
}
