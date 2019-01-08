package pl.coderslab.starter.edtityes;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameExercise;


    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;


    @OneToMany
    @JoinColumn(name = "id_step")
    private List<Steps> stepsList;

    public Exercise() {
    }

    public List<Steps> getStepsList() {
        return stepsList;
    }

    public void setStepsList(List<Steps> stepsList) {
        this.stepsList = stepsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameExercise() {
        return nameExercise;
    }

    public void setNameExercise(String nameExercise) {
        this.nameExercise = nameExercise;
    }
//
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


}
