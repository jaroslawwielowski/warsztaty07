package pl.coderslab.starter.edtityes;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.mindrot.jbcrypt.BCrypt;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.data.annotation.CreatedBy;
import pl.coderslab.starter.enums.UserPrivileges;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @NotBlank
    @NotEmpty
    @Column(unique=true)
    private String login;


    @NotBlank
    @NotEmpty
    @Email(regexp = ".+")
    @Column(unique=true)
    private String email;



    @JoinTable(name = "user_project", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_project"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Project> projects;



    @OneToMany
    private List<Steps> stepsList;



    @NotEmpty
    private String password;

    private String administrativeRights;
    private boolean confirmationStatus;
    private String confirmationId;
    private String confirmationOnlineId;
    private boolean online;

    private String trace ;

    public User() {
    }


    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getAdministrativeRights() {
        return administrativeRights;
    }

    public void setAdministrativeRights(String administrativeRights) {
        this.administrativeRights = administrativeRights;
    }

    public void setPassword(String password) {
        // NOTE w przypadku gdy hasło jest pustym stringiem/nullem, funkcja hashpw zadziała i wygeneruje
        // nie-nullowego hasha
        // chcemy tego uniknąć:)
        this.password =
                password == null || password.isEmpty()
                        ? password
                        : BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean passwordMatches(String plainTextPassword) {
        return BCrypt.checkpw(plainTextPassword, this.password);
    }

    public String getConfirmationOnlineId() {
        return confirmationOnlineId;
    }

    public void setConfirmationOnlineId(String confirmationOnlineId) {
        this.confirmationOnlineId = confirmationOnlineId;
    }


    public List<Steps> getStepsList() {
        return stepsList;
    }

    public void setStepsList(List<Steps> stepsList) {
        this.stepsList = stepsList;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }


    public boolean isConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(boolean confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", createDateTime=" + createDateTime +
//                ", login='" + login + '\'' +
//                ", email='" + email + '\'' +
//                ", projects=" + projects +
//                ", stepsList=" + stepsList +
//
//                '}';
//    }
}
