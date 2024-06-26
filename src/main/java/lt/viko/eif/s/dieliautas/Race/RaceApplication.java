package lt.viko.eif.s.dieliautas.Race;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.net.URI;

/**
 * Pagrindinė klasė, skirta paleisti Spring Boot aplikaciją.
 */
@SpringBootApplication
public class RaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaceApplication.class, args);
    }

    /**
     * Įvyksta kai aplikacija yra pilnai paruošta ir atidaro pagrindinį puslapį naršyklėje.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void openMainPage() {
        String url = "http://localhost:8080/";
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                String os = System.getProperty("os.name").toLowerCase();
                Runtime runtime = Runtime.getRuntime();
                if (os.contains("win")) {
                    runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
                } else if (os.contains("mac")) {
                    runtime.exec("open " + url);
                } else if (os.contains("nix") || os.contains("nux")) {
                    runtime.exec("xdg-open " + url);
                } else {
                    throw new UnsupportedOperationException("Unknown operating system: " + os);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
