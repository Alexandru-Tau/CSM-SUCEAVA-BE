package ro.usv.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.backend.model.Match;
import ro.usv.backend.model.Player;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Match save(Player player);

    Optional<Match> findById(Long id);
}

