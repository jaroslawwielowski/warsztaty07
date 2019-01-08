package pl.coderslab.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.starter.edtityes.Exercise;
import pl.coderslab.starter.edtityes.Project;


public interface ExerciseRepository extends JpaRepository<Exercise, Long> {


}
