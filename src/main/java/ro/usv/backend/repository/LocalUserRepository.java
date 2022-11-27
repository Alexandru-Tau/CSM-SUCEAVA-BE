package ro.usv.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.backend.model.LocalUser;

import java.util.Optional;

@Repository
public interface LocalUserRepository extends JpaRepository< LocalUser, Long> {
    Optional<LocalUser> findByEmail(String email);
}
