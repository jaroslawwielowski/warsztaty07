package pl.coderslab.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.starter.edtityes.Project;
import pl.coderslab.starter.edtityes.User;

import java.awt.*;
import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project> getProjectsByUsers(User user);
//    public  List<Project> findByUsers(User user);

    @Query("select p from Project p where p.users =?1")
    public  List<Project> findProjectsByUsers(Long id);



}
