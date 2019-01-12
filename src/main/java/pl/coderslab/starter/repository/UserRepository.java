package pl.coderslab.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.starter.edtityes.Project;
import pl.coderslab.starter.edtityes.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User getUserByConfirmationId(String confirmationId);

    public User getUserByEmail(String email);

    public User getUserByConfirmationOnlineId(String confirmationOnlineId);

    public User getUserById(Long id);

    public List<User> findUsersByProjects(Project project);

}
