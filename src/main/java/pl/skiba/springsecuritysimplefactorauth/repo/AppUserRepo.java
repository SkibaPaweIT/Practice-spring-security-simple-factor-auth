package pl.skiba.springsecuritysimplefactorauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skiba.springsecuritysimplefactorauth.model.appUser;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<appUser, Long> {

    Optional<appUser> findByUsername(String username);
}
