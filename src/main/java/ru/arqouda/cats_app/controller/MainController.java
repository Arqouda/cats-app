package ru.arqouda.cats_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.arqouda.cats_app.DTO.CatDTO;
import ru.arqouda.cats_app.model.Cat;
import ru.arqouda.cats_app.repository.CatRepo;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "main_methods")
public class MainController {

    @Autowired
    private CatRepo catRepo;

    @Operation(
            summary = "кладет нового котика в базу",
            description = "Получает DTO кота и билдером собирает и сохраняет сущность в базу"
    )
    @PostMapping("api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        log.info(
                "New row: " + catRepo.save(
                        Cat.builder()
                                .age(catDTO.getAge())
                                .weight(catDTO.getWeight())
                                .name(catDTO.getName())
                                .build())
        );
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
    public void deleteCat(@RequestParam Long id) {
        catRepo.deleteById(id);
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestParam Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "This cat as exist";
        }
        return catRepo.save(cat).toString();
    }

}
