package lt.viko.eif.s.dieliautas.Race.controller;

import lt.viko.eif.s.dieliautas.Race.service.DatabaseService;
import lt.viko.eif.s.dieliautas.Race.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Pagrindinis valdiklis, atsakingas už tinklalapio navigaciją ir duomenų valdymą.
 */
@Controller
public class MainController {

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private RacerRepository racerRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private RaceInfoRepository raceInfoRepository;

    @Autowired
    private DatabaseService databaseService;

    /**
     * Rodo pagrindinį puslapį.
     *
     * @return pagrindinio puslapio šablono pavadinimas.
     */
    @GetMapping("/")
    public String homePage() {
        return "main";
    }

    /**
     * Rodo lenktynių sąrašą.
     *
     * @param model Modelio objektas, naudojamas perkelti duomenis į vaizdą.
     * @return lenktynių puslapio šablono pavadinimas.
     */
    @GetMapping("/races")
    public String showRaces(Model model) {
        model.addAttribute("races", raceRepository.findAll());
        return "races";
    }

    /**
     * Rodo lenktynininkų sąrašą.
     *
     * @param model Modelio objektas, naudojamas perkelti duomenis į vaizdą.
     * @return lenktynininkų puslapio šablono pavadinimas.
     */
    @GetMapping("/racers")
    public String showRacers(Model model) {
        model.addAttribute("racers", racerRepository.findAll());
        return "racers";
    }

    /**
     * Rodo statusų sąrašą.
     *
     * @param model Modelio objektas, naudojamas perkelti duomenis į vaizdą.
     * @return statusų puslapio šablono pavadinimas.
     */
    @GetMapping("/statuses")
    public String showStatuses(Model model) {
        model.addAttribute("statuses", statusRepository.findAll());
        return "statuses";
    }

    /**
     * Rodo lenktynių informacijų sąrašą.
     *
     * @param model Modelio objektas, naudojamas perkelti duomenis į vaizdą.
     * @return lenktynių informacijos puslapio šablono pavadinimas.
     */
    @GetMapping("/raceinfos")
    public String showRaceInfos(Model model) {
        model.addAttribute("raceinfos", raceInfoRepository.findAll());
        return "raceinfos";
    }

    /**
     * Užkrauna duomenų bazę su pradiniais duomenimis.
     *
     * @return peradresuoja į pagrindinį puslapį.
     */
    @PostMapping("/loaddatabase")
    public String loadDatabase() {
        databaseService.initializeDatabase();
        return "redirect:/";
    }

    /**
     * Peradresuoja į Swagger UI.
     *
     * @return Swagger UI URL adresas.
     */
    @GetMapping("/swagger")
    public String showSwagger() {
        return "redirect:/swagger-ui.html";
    }
}
