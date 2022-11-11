package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.NewsDto;
import ro.usv.backend.model.Hashtag;
import ro.usv.backend.model.News;
import ro.usv.backend.repository.HashtagsRepository;
import ro.usv.backend.repository.NewsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final HashtagsRepository hashtagsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository, HashtagsRepository hashtagsRepository) {
        this.newsRepository = newsRepository;
        this.hashtagsRepository = hashtagsRepository;
    }

    public NewsDto create(NewsDto newsDto) {
        News news = dtoToModel(newsDto, null);
        News response = newsRepository.save(news);
        List<Hashtag> hashtagList = getHashtags(response.getDescription(), response);
        hashtagsRepository.saveAll(hashtagList);
        return modelToDto(response);
    }

    public NewsDto update(NewsDto newsDto, Long id) {
        News news = dtoToModel(newsDto, id);
        News response = newsRepository.save(news);
        List<Hashtag> hashtagList = getHashtags(response.getDescription(), response);
        hashtagsRepository.saveAll(hashtagList);
        return modelToDto(response);
    }

    public NewsDto read(Long value) {
        Optional<News> news = newsRepository.findById(value);
        if (news.isPresent()) {
            News response = news.get();
            return modelToDto(response);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<NewsDto> readAll() {

        return newsRepository.findAll().stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        newsRepository.deleteById(id);
    }

    public List<NewsDto> findAllByHashtag(String value) {
        return hashtagsRepository.findAllByTag(value)
                .stream()
                .map(newsRepository::findAllByHashtags)
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public NewsDto findByTitle(String value) {
        Optional<News> news = newsRepository.findByTitle(value);
        if (news.isPresent()) {
            News response = news.get();
            return modelToDto(response);
        } else {
            return null;
        }
    }

    private List<Hashtag> getHashtags(String description, News news) {

        Pattern MY_PATTERN = Pattern.compile("#(\\w+)");
        Matcher mat = MY_PATTERN.matcher(description);
        List<String> strs = new ArrayList<>();
        while (mat.find()) {
            strs.add(mat.group(1));

        }

        List<Hashtag> hashtags = new ArrayList<>();
        for (String str : strs) {
            Hashtag hashtag = new Hashtag();
            hashtag.setTag(str);
            hashtag.setNews(news);
            hashtags.add(hashtag);
        }
        return hashtags;
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

    News dtoToModel(NewsDto dto, Long id) {
        News news = new News();
        news.setId(id);
        news.setTitle(dto.getTitle());
        news.setDescription(dto.getDescription());
        news.setIsPosted(isPosted(dto.getIsDraft(), dto.getDateTime()));
        news.setIsAppointed(isAppointed(dto.getIsDraft(), news.getIsPosted()));
        news.setDateTime(dto.getDateTime());
        news.setIsDraft(dto.getIsDraft());
        news.setNewsType(dto.getNewsType());
        return news;
    }

    NewsDto modelToDto(News model) {
        return new NewsDto(model.getId(), model.getTitle(), model.getDescription(),
                model.getDateTime(), model.getIsDraft(), model.getNewsType());

    }

}
