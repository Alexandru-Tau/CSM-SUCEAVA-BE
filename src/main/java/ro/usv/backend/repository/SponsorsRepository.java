package ro.usv.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.backend.model.Sponsors;

import java.util.Optional;

@Repository
public interface SponsorsRepository extends JpaRepository<Sponsors, Long> {

    Sponsors save(Sponsors sponsors);

    Optional<Sponsors> findById(Long id);
}
