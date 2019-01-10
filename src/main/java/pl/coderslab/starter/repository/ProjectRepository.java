package pl.coderslab.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.starter.edtityes.Project;
import pl.coderslab.starter.edtityes.User;

import java.awt.*;
import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project> getProjectsByUsers(Long id);

}
