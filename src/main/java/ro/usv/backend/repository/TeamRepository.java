package ro.usv.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.backend.model.Team;

import java.util.Optional;
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findById(Long id);

}
