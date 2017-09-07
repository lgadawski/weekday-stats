package com.gadawski.webapp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("restTemplateImpl")
    private RestTemplate restTemplate;

    @Override
    public boolean isLgPresent() {
        return getUSers();
    }

    private boolean getUSers() {
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = call();
        } catch (HttpStatusCodeException e) {
            logger.error(e);

            throw new RuntimeException();
        }

        String body = responseEntity.getBody();
        JsonParser parser = new JsonParser();
        JsonObject parsed = parser.parse(body).getAsJsonObject();

        String state = getStringOrNull("state", parsed);

        return "home".equals(state);

    }

    private String getStringOrNull(String property, JsonObject parsed) {
        JsonElement jsonElement = parsed.get(property);
        if (jsonElement.isJsonNull()) {
            return "";
        }

        return jsonElement.getAsString();
    }

    private ResponseEntity<String> call() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return restTemplate.exchange(
                "http://localhost:8123/api/states/device_tracker.78f8829e9232",
                HttpMethod.GET,
                entity,
                String.class);
    }
}
