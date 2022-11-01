package ro.usv.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.backend.model.News;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News save(News news); //update si create
    List<News> findAll();// read all
}
