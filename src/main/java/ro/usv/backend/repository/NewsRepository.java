package ro.usv.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.backend.model.Hashtag;
import ro.usv.backend.model.News;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
  //update si create
    List<News> findAllByHashtags(Hashtag hastags);// read all
    Optional<News> findById(Long id);
    Optional<News> findByTitle(String title);


}
