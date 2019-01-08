package pl.coderslab.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.starter.edtityes.Project;
import pl.coderslab.starter.edtityes.Steps;


public interface StepsRepository extends JpaRepository<Steps, Long> {


}
