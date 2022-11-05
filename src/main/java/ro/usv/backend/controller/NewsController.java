package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.usv.backend.dto.NewsDto;
import ro.usv.backend.service.NewsService;

import java.util.List;

@RestController("/api/v0/news")
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public NewsDto createNews(@RequestBody NewsDto news) {
        return newsService.create(news);
    }

    @GetMapping("/hashtag/{value}")
    public List<NewsDto> findAllByHashtag(@RequestParam String value) {
        return newsService.findAllByHashtag(value);
    }

    @GetMapping("/id/{value}")
    public NewsDto findById(@RequestParam Long value) {
        return newsService.read(value);
    }

    @GetMapping("/title/{value}")
    public NewsDto findByTitle(@RequestParam String value) {
        return newsService.findByTitle(value);
    }

    @GetMapping
    public List<NewsDto> getAll() {
        return newsService.readAll();
    }

    @PostMapping("/id/{id}")
    public NewsDto update(@RequestBody NewsDto news, @RequestParam Long id) {
        return newsService.update(news, id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@RequestParam Long id) {
        newsService.delete(id);
    }

}
