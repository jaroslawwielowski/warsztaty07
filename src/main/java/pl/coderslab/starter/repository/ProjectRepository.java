package pl.coderslab.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.starter.edtityes.Project;
import pl.coderslab.starter.edtityes.User;

import java.awt.*;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project> getProjectsByUsers(User user);



}
