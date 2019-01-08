package pl.coderslab.starter.edtityes;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "stepss")
public class Steps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameSpeps;

//    private Project stepProject;
//
//    private Exercise stepExercise;
    private String stepss;

    public Steps() {
    }

    public String getStepss() {
        return stepss;
    }

    public void setStepss(String stepss) {
        this.stepss = stepss;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSpeps() {
        return nameSpeps;
    }

    public void setNameSpeps(String nameSpeps) {
        this.nameSpeps = nameSpeps;
    }

//    public Project getStepProject() {
//        return stepProject;
//    }
//
//    public void setStepProject(Project stepProject) {
//        this.stepProject = stepProject;
//    }
//
//    public Exercise getStepExercise() {
//        return stepExercise;
//    }
//
//    public void setStepExercise(Exercise stepExercise) {
//        this.stepExercise = stepExercise;
//    }
}
