package lt.viko.eif.s.dieliautas.Race.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostmanCollectionService {

    /**
     * Generuoja Postman kolekciją.
     *
     * @return Postman kolekcijos duomenų žemėlapis.
     */
    public Map<String, Object> generatePostmanCollection() {
        Map<String, Object> postmanCollection = new HashMap<>();
        postmanCollection.put("info", createInfo());
        postmanCollection.put("item", createFolders());
        return postmanCollection;
    }

    /**
     * Sukuria informaciją apie Postman kolekciją.
     *
     * @return Informacijos žemėlapis.
     */
    private Map<String, Object> createInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "Race Service REST API");
        info.put("_postman_id", UUID.randomUUID().toString());
        info.put("description", "Collection for Race Service REST API");
        info.put("schema", "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
        return info;
    }

    /**
     * Sukuria aplankus su API užklausomis.
     *
     * @return Aplankų sąrašas.
     */
    private List<Map<String, Object>> createFolders() {
        List<Map<String, Object>> folders = new ArrayList<>();

        folders.add(createFolder("RaceInfo", createRaceInfoItems()));
        folders.add(createFolder("Racer", createRacerItems()));
        folders.add(createFolder("Race", createRaceItems()));
        folders.add(createFolder("Status", createStatusItems()));

        return folders;
    }

    /**
     * Sukuria aplanką su nurodytu pavadinimu ir API užklausomis.
     *
     * @param name Aplanko pavadinimas.
     * @param items API užklausų sąrašas.
     * @return Aplanko žemėlapis.
     */
    private Map<String, Object> createFolder(String name, List<Map<String, Object>> items) {
        Map<String, Object> folder = new HashMap<>();
        folder.put("name", name);
        folder.put("item", items);
        return folder;
    }

    /**
     * Sukuria API užklausas lenktynių informacijai.
     *
     * @return API užklausų sąrašas.
     */
    private List<Map<String, Object>> createRaceInfoItems() {
        List<Map<String, Object>> items = new ArrayList<>();

        items.add(createItem("Get all RaceInfos", "GET", "/api/raceInfos"));
        items.add(createItem("Get RaceInfo by ID", "GET", "/api/raceInfos/:id", ":id", "RaceInfo ID"));
        items.add(createItem("Create RaceInfo", "POST", "/api/raceInfos", null, "{\"race\": {\"id\": 1}, \"racer\": {\"id\": 1}, \"finishTime\": \"01:30:00\", \"positionNumber\": 1}"));
        items.add(createItem("Update RaceInfo", "PUT", "/api/raceInfos/:id", ":id", "{\"race\": {\"id\": 1}, \"racer\": {\"id\": 1}, \"finishTime\": \"01:35:00\", \"positionNumber\": 2}"));
        items.add(createItem("Delete RaceInfo", "DELETE", "/api/raceInfos/:id", ":id", "RaceInfo ID"));

        return items;
    }

    /**
     * Sukuria API užklausas lenktynininkams.
     *
     * @return API užklausų sąrašas.
     */
    private List<Map<String, Object>> createRacerItems() {
        List<Map<String, Object>> items = new ArrayList<>();

        items.add(createItem("Get all Racers", "GET", "/api/racers"));
        items.add(createItem("Get Racer by ID", "GET", "/api/racers/:id", ":id", "Racer ID"));
        items.add(createItem("Create Racer", "POST", "/api/racers", null, "{\"firstName\": \"John\", \"lastName\": \"Doe\", \"phoneNumber\": \"1234567890\", \"status\": {\"id\": 1}}"));
        items.add(createItem("Update Racer", "PUT", "/api/racers/:id", ":id", "{\"firstName\": \"Jane\", \"lastName\": \"Doe\", \"phoneNumber\": \"0987654321\", \"status\": {\"id\": 1}}"));
        items.add(createItem("Delete Racer", "DELETE", "/api/racers/:id", ":id", "Racer ID"));
        items.add(createItem("Get Racers by Status ID", "GET", "/api/racers/status/:statusId", ":statusId", "Status ID"));

        return items;
    }

    /**
     * Sukuria API užklausas lenktynėms.
     *
     * @return API užklausų sąrašas.
     */
    private List<Map<String, Object>> createRaceItems() {
        List<Map<String, Object>> items = new ArrayList<>();

        items.add(createItem("Get all Races", "GET", "/api/races"));
        items.add(createItem("Get Race by ID", "GET", "/api/races/:id", ":id", "Race ID"));
        items.add(createItem("Create Race", "POST", "/api/races", null, "{\"name\": \"Spring Marathon\", \"location\": \"New York\", \"date\": \"2023-05-28\"}"));
        items.add(createItem("Update Race", "PUT", "/api/races/:id", ":id", "{\"name\": \"Summer Marathon\", \"location\": \"Los Angeles\", \"date\": \"2023-06-15\"}"));
        items.add(createItem("Delete Race", "DELETE", "/api/races/:id", ":id", "Race ID"));
        items.add(createItem("Get Races by Date Range", "GET", "/api/races/daterange?startDate=2023-05-01&endDate=2023-06-01"));

        return items;
    }

    /**
     * Sukuria API užklausas statusams.
     *
     * @return API užklausų sąrašas.
     */
    private List<Map<String, Object>> createStatusItems() {
        List<Map<String, Object>> items = new ArrayList<>();

        items.add(createItem("Get all Statuses", "GET", "/api/statuses"));
        items.add(createItem("Get Status by ID", "GET", "/api/statuses/:id", ":id", "Status ID"));
        items.add(createItem("Create Status", "POST", "/api/statuses", null, "{\"statusName\": \"Active\"}"));
        items.add(createItem("Update Status", "PUT", "/api/statuses/:id", ":id", "{\"statusName\": \"Inactive\"}"));
        items.add(createItem("Delete Status", "DELETE", "/api/statuses/:id", ":id", "Status ID"));

        return items;
    }

    /**
     * Sukuria API užklausą.
     *
     * @param name API užklausos pavadinimas.
     * @param method HTTP metodas.
     * @param url API URL.
     * @return API užklausos žemėlapis.
     */
    private Map<String, Object> createItem(String name, String method, String url) {
        return createItem(name, method, url, null, null);
    }

    /**
     * Sukuria API užklausą su kūno duomenimis.
     *
     * @param name API užklausos pavadinimas.
     * @param method HTTP metodas.
     * @param url API URL.
     * @param urlVariable URL kintamasis.
     * @param body Užklausos kūno duomenys.
     * @return API užklausos žemėlapis.
     */
    private Map<String, Object> createItem(String name, String method, String url, String urlVariable, String body) {
        Map<String, Object> item = new HashMap<>();
        item.put("name", name);

        Map<String, Object> request = new HashMap<>();
        request.put("method", method);
        request.put("header", new ArrayList<>());

        if (body != null) {
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("mode", "raw");
            bodyMap.put("raw", body);
            bodyMap.put("options", Map.of("raw", Map.of("language", "json")));
            request.put("body", bodyMap);
        }

        Map<String, Object> urlMap = new HashMap<>();
        urlMap.put("raw", "{{baseUrl}}" + url);
        urlMap.put("host", List.of("{{baseUrl}}"));
        urlMap.put("path", Arrays.asList(url.split("/")).subList(1, url.split("/").length));

        if (urlVariable != null) {
            Map<String, Object> variable = new HashMap<>();
            variable.put("key", urlVariable);
            variable.put("value", "1");
            variable.put("description", name + " " + urlVariable);
            urlMap.put("variable", List.of(variable));
        }

        request.put("url", urlMap);
        item.put("request", request);
        return item;
    }
}
