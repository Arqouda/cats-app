package ru.arqouda.cats_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arqouda.cats_app.model.Cat;

public interface CatRepo extends JpaRepository<Cat, Long> {
}
