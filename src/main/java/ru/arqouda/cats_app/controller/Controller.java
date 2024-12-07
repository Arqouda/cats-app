package ru.arqouda.cats_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.arqouda.cats_app.kafka.KafkaProducer;
import ru.arqouda.cats_app.repository.CatRepo;

@RestController
public class Controller {
    private final KafkaProducer kafkaProducer;

    @Autowired
    CatRepo catRepo;

    public Controller(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/kafka/send")
    public String send(@RequestParam long id) {
        var cat = catRepo.findById(id).orElseThrow();
        kafkaProducer.sendMessage(cat.toString());
        return "Success";
    }

}
