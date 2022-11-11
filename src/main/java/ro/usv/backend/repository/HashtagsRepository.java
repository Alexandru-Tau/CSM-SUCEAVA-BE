package ro.usv.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.backend.model.Hashtag;

import java.util.List;

@Repository
public interface HashtagsRepository extends JpaRepository<Hashtag, Long> {

    List<Hashtag> findAllByTag(String tag);
}
