package ro.usv.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.backend.model.Sponsors;
import ro.usv.backend.model.Team;

import java.util.Optional;
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team save(Team team);

    Optional<Team> findById(Long id);

}
