package ro.usv.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.backend.model.Award;

import java.util.Optional;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

    Optional<Award> findById(Long id);

}
