package pl.skorupska.springsecuritysimplefactorauthorization.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skorupska.springsecuritysimplefactorauthorization.model.UserApp;

import java.util.Optional;

@Repository
public interface UserAppRepo extends JpaRepository<UserApp, Long> {

    Optional<UserApp> findByUsername(String username);

}
