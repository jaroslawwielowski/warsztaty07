package pl.coderslab.starter.dto;

import org.hibernate.annotations.CreationTimestamp;
import pl.coderslab.starter.edtityes.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @NotBlank
    @NotEmpty
    private String login;



    @NotBlank
    @NotEmpty
    @Email(regexp = ".+")
    @Column(unique=true)
    private String email;


    @NotEmpty
    @Size(min = 2, max = 30, message = "hasło musi posiadac od 2 do 30 znaków")
    private String password;


    public UserDto(LocalDateTime createDateTime, @NotBlank @NotEmpty String login, @NotBlank @NotEmpty @Email(regexp = ".+") String email, @NotEmpty @Size(min = 2, max = 30, message = "hasło musi posiadac od 2 do 30 znaków") String password) {
        this.createDateTime = createDateTime;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public UserDto() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public User toUser(){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setLogin(login);
        return user;
    }
}
