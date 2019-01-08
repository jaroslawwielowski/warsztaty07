package pl.coderslab.starter.edtityes;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "steps")
public class Steps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameSpeps;
    private int indexSteps;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User stepsUser;

    @ManyToOne
    @JoinColumn(name = "id_exercise")
    private Exercise stepExercise;


    private String stepstostep;

    public Steps() {
    }

    public Long getId() {
        return id;
    }

    public int getIndexSteps() {
        return indexSteps;
    }

    public void setIndexSteps(int indexSteps) {
        this.indexSteps = indexSteps;
    }

    public User getStepsUser() {
        return stepsUser;
    }

    public void setStepsUser(User stepsUser) {
        this.stepsUser = stepsUser;
    }

    public Exercise getStepExercise() {
        return stepExercise;
    }

    public void setStepExercise(Exercise stepExercise) {
        this.stepExercise = stepExercise;
    }

    public String getStepstostep() {
        return stepstostep;
    }

    public void setStepstostep(String stepstostep) {
        this.stepstostep = stepstostep;
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


}
