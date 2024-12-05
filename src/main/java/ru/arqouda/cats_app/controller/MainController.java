package ru.arqouda.cats_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.arqouda.cats_app.model.Cat;
import ru.arqouda.cats_app.repository.CatRepo;

import java.util.List;

@RestController
@Slf4j
public class MainController {

    @Autowired
    private CatRepo catRepo;

    @PostMapping("api/add")
    public void addCat(@RequestBody Cat cat) {
        log.info("New row: " + catRepo.save(cat));
    }

    @GetMapping("api/all")
    public List<Cat> getAllCat() {
        return catRepo.findAll();
    }

    @GetMapping("api/")
    public Cat getCat(@RequestParam Long id) {
        return catRepo.findById(id).orElseThrow();
    }
    @DeleteMapping("api/")
    public void deleteCat(@RequestParam Long id){
        catRepo.deleteById(id);
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestParam Cat cat) {
        if(!catRepo.existsById(cat.getId())){
             return "This cat as exist";
        }
        return catRepo.save(cat).toString();
    }

}
