package ro.usv.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.NewsDto;
import ro.usv.backend.model.News;
import ro.usv.backend.model.NewsType;
import ro.usv.backend.repository.HashtagsRepository;
import ro.usv.backend.repository.NewsRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NewsServiceTest {
    @Mock
    private NewsRepository newsRepositoryMock;
    @Mock
    private HashtagsRepository hashtagsRepositoryMock;

    private NewsService newsService;

    @BeforeEach
    void setup() {
        newsService = new NewsService(newsRepositoryMock, hashtagsRepositoryMock);
    }

    @Test
    void testCreate() {
        // given
        NewsDto newsDto = new NewsDto(1L, "Stire1", "asd", null, true, NewsType.TEXT);
        News news = new News(1L, "Stire1", "asd", false, true, null, false, NewsType.TEXT, null);
        when(newsRepositoryMock.save(any())).thenReturn(news);
        // when
        newsService.create(newsDto);
        // then
        verify(newsRepositoryMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        // given
        NewsDto newsDto = new NewsDto(1L, "Stire1", "asd", null, true, NewsType.TEXT);
        News news = new News(1L, "Stire1", "asd", false, true, null, false, NewsType.TEXT, null);
        when(newsRepositoryMock.save(any())).thenReturn(news);
        // when
        newsService.update(newsDto, 1L);
        // then
        verify(newsRepositoryMock, times(1)).save(any());

    }

    @Test
    void testFindById() {
        // given
        News news = new News(1L, "Stire1", "asd", false, true, null, false, NewsType.TEXT, null);
        when(newsRepositoryMock.findById(any())).thenReturn(Optional.of(news));
        // when
        newsService.read(1L);
        // then
        verify(newsRepositoryMock, times(1)).findById(any());
    }

    @Test
    void testFindByIdNotExisting() {
        // given
        when(newsRepositoryMock.findById(any())).thenReturn(Optional.empty());
        // when //then
        assertThatThrownBy(() -> newsService.read(1L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("News Not Found");
        verify(newsRepositoryMock, times(1)).findById(any());

    }

    @Test
    void testReadAll() {
        // given
        News news = new News(1L, "Stire1", "asd", false, true, null, false, NewsType.TEXT, null);
        when(newsRepositoryMock.findAll()).thenReturn(List.of(news));
        // when
        newsService.readAll();
        // then
        verify(newsRepositoryMock, times(1)).findAll();

    }

    @Test
    void testDelete() {

        // when
        newsService.delete(1L);
        // then
        verify(newsRepositoryMock, times(1)).deleteById(1L);

    }

    @Test
    void testMapping() {
        // given //datele care le iei de pe site
        NewsDto inputDto = new NewsDto(1L, "Stire1", "asd", null, true, NewsType.TEXT);
        News outputModel = newsService.dtoToModel(inputDto, 2L);
        assertThat(outputModel.getId()).isEqualTo(2L);
        assertThat(outputModel.getTitle()).isEqualTo(inputDto.getTitle());
        assertThat(outputModel.getDescription()).isEqualTo(inputDto.getDescription());
        assertThat(outputModel.getDateTime()).isEqualTo(inputDto.getDateTime());
        assertThat(outputModel.getIsDraft()).isEqualTo(inputDto.getIsDraft());
        assertThat(outputModel.getNewsType()).isEqualTo(inputDto.getNewsType());


        //        assertThat(outputModel.getEdition())
        // cand iei din db si pui pe site
        News inputModel = new News(1L, "Stire1", "asd", false, true, null, false, NewsType.TEXT, null);
        NewsDto outputDto = newsService.modelToDto(inputModel);
        assertThat(outputDto.getId()).isEqualTo(inputModel.getId());
        assertThat(outputDto.getTitle()).isEqualTo(inputModel.getTitle());
        assertThat(outputDto.getDescription()).isEqualTo(inputModel.getDescription());
        assertThat(outputDto.getDateTime()).isEqualTo(inputModel.getDateTime());
        assertThat(outputDto.getIsDraft()).isEqualTo(inputModel.getIsDraft());
        assertThat(outputDto.getNewsType()).isEqualTo(inputModel.getNewsType());


    }
}
