package pl.coderslab.starter.edtityes;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameProject;


    @OneToMany
    private List<Exercise> exerciseList;

    private int status;

    @ManyToMany(mappedBy = "projects")
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


//    @Override
//    public String toString() {
//        return "Project{" +
//                "id=" + id +
//                ", nameProject='" + nameProject + '\'' +
//                ", exerciseList=" + exerciseList +
//                ", status=" + status +
//                ", users=" + users +
//                '}';
//    }
}
