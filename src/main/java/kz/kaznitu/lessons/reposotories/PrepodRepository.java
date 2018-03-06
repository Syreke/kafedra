package kz.kaznitu.lessons.reposotories;

import kz.kaznitu.lessons.models.Prepod;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PrepodRepository extends CrudRepository<Prepod, Long> {
        Optional<Prepod> findById(Long id);
    }
