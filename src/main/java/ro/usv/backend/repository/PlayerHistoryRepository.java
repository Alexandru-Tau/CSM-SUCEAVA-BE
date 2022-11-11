package ro.usv.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.backend.model.PlayerHistory;

import java.util.Optional;

@Repository
public interface PlayerHistoryRepository extends JpaRepository<PlayerHistory, Long> {

    Optional<PlayerHistory> findById(Long id);

}
