package pl.coderslab.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.starter.edtityes.Project;
import pl.coderslab.starter.edtityes.User;


public interface ProjectRepository extends JpaRepository<Project, Long> {

    public Project getProjectsById(Long id);

}
