package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.usv.backend.dto.NewsDto;
import ro.usv.backend.model.News;
import ro.usv.backend.repository.NewsRepository;

import java.time.LocalDateTime;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public NewsDto create(NewsDto newsDto) {
        News news = new News();
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setDescription(newsDto.getDescription());
        news.setIsPosted(isPosted(newsDto.getIsDraft(), newsDto.getDateTime()));
        news.setIsAppointed(isAppointed(newsDto.getIsDraft(), news.getIsPosted()));
        news.setDateTime(newsDto.getDateTime());
        news.setIsDraft(newsDto.getIsDraft());
        news.setNewsType(newsDto.getNewsType());

        News response = newsRepository.save(news);
        return new NewsDto(response.getId(), response.getTitle(), response.getDescription(),
                response.getDateTime(), response.getIsDraft(), response.getNewsType());

    }

    private Boolean isAppointed(Boolean isDraft, Boolean isPosted) {
        if (isPosted) {
            return false;
        } else {
            return !isDraft;
        }
    }

    private Boolean isPosted(Boolean isDraft, LocalDateTime dateTime) {
        if (isDraft) {
            return false;
        } else {
            return !LocalDateTime.now().isBefore(dateTime);
        }
    }
}
