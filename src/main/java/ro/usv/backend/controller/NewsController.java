package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.usv.backend.dto.NewsDto;
import ro.usv.backend.service.NewsService;

@RestController("/api/v0/news")
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    @PostMapping
    public NewsDto createNews(@RequestBody NewsDto news)
    {
        return newsService.create(news);
    }
}
