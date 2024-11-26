package ru.arqouda.cats_app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.arqouda.cats_app.model.Cat;

@RestController
public class MainController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/api/main")
    public String mainListener() {
        return "Hello World";
    }

    @GetMapping("api/cat")
    public String giveCat() {
        Cat cat = Cat.builder()
                .age(1)
                .name("Terrorblade")
                .weight(2)
                .build();
        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(cat);
        } catch (JsonProcessingException e) {
            System.out.println("Error withs cat");
        }
        return jsonData;
    }
    @PostMapping("api/special")
    public String givSpecialCat(@RequestParam String name){
        Cat cat = Cat.builder()
                .age(1)
                .name(name)
                .weight(2)
                .build();
        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(cat);
        } catch (JsonProcessingException e) {
            System.out.println("Error withs cat");
        }
        return jsonData;
    }
}
