package lt.viko.eif.s.dieliautas.Race.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.viko.eif.s.dieliautas.Race.service.PostmanCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Valdiklis, atsakingas už Postman kolekcijos generavimą ir parsisiuntimą.
 */
@RestController
@RequestMapping("/api")
public class PostmanCollectionController {

    @Autowired
    private PostmanCollectionService postmanCollectionService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Metodas, kuris generuoja Postman kolekciją ir leidžia ją parsisiųsti kaip JSON failą.
     *
     * @return ResponseEntity su InputStreamResource, kuriame yra Postman kolekcijos JSON failas.
     * @throws IOException jei įvyksta klaida generuojant JSON.
     */
    @GetMapping("/postman-collection")
    public ResponseEntity<InputStreamResource> getPostmanCollection() throws IOException {
        Map<String, Object> postmanCollection = postmanCollectionService.generatePostmanCollection();
        String postmanCollectionJson = objectMapper.writeValueAsString(postmanCollection);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(postmanCollectionJson.getBytes());
        InputStreamResource resource = new InputStreamResource(byteArrayInputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=postman_collection.json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(postmanCollectionJson.length())
                .body(resource);
    }
}
