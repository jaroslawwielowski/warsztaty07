package pl.coderslab.starter.edtityes;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameProject;


//    private List<Exercise> exerciseList;

//    private List<Steps> stepsList;


    @ManyToMany
    private List<User> userList;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    //    public List<Exercise> getExerciseList() {
//        return exerciseList;
//    }
//
//    public void setExerciseList(List<Exercise> exerciseList) {
//        this.exerciseList = exerciseList;
//    }
//
//    public List<Steps> getStepsList() {
//        return stepsList;
//    }
//
//    public void setStepsList(List<Steps> stepsList) {
//        this.stepsList = stepsList;
//    }
}
