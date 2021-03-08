package pl.skorupska.springsecuritysimplefactorauthorization.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skorupska.springsecuritysimplefactorauthorization.model.Token;

import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

    //todo Optional wyjatki
    Optional<Token> findByValue(String value);
}
