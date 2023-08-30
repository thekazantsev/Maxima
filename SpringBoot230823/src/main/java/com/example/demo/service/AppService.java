package com.example.demo.service;

import com.google.gson.JsonObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    public ResponseEntity<String> post(String name) {
        JsonObject jsonObject = new JsonObject();

        if (!name.contains("test")) {
            jsonObject.addProperty("name", name);
            jsonObject.addProperty("coder", true);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonObject.toString());
        } else {
            jsonObject.addProperty("error", "'test' detected");
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonObject.toString());
        }
    }
}
