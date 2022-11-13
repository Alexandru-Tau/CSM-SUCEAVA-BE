package ro.usv.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.PlayerHistoryDto;
import ro.usv.backend.model.PlayerHistory;
import ro.usv.backend.repository.PlayerHistoryRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerHistoryServiceTest {
    @Mock
    private PlayerHistoryRepository playerHistoryRepositoryMock;

    private PlayerHistoryService playerHistoryService;

    @BeforeEach
    void setup() {
        playerHistoryService = new PlayerHistoryService(playerHistoryRepositoryMock);
    }

    @Test
    void testCreate() {
        // given
        PlayerHistoryDto playerHistoryDto = new PlayerHistoryDto(1L, "atacnt", "2022-2023");
        PlayerHistory playerHistory = new PlayerHistory(1L, "atacnt", "2022-2023", null, null);
        when(playerHistoryRepositoryMock.save(any())).thenReturn(playerHistory);
        // when
        playerHistoryService.create(playerHistoryDto);
        // then
        verify(playerHistoryRepositoryMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        // given
        PlayerHistoryDto playerHistoryDto = new PlayerHistoryDto(1L, "atacnt", "2022-2023");
        PlayerHistory playerHistory = new PlayerHistory(1L, "atacnt", "2022-2023", null, null);
        when(playerHistoryRepositoryMock.save(any())).thenReturn(playerHistory);
        // when
        playerHistoryService.update(playerHistoryDto, 1L);
        // then
        verify(playerHistoryRepositoryMock, times(1)).save(any());

    }

    @Test
    void testFindById() {
        // given
        PlayerHistory playerHistory = new PlayerHistory(1L, "atacnt", "2022-2023", null, null);
        when(playerHistoryRepositoryMock.findById(any())).thenReturn(Optional.of(playerHistory));
        // when
        playerHistoryService.read(1L);
        // then
        verify(playerHistoryRepositoryMock, times(1)).findById(any());
    }

    @Test
    void testFindByIdNotExisting() {
        // given
        when(playerHistoryRepositoryMock.findById(any())).thenReturn(Optional.empty());
        // when //then
        assertThatThrownBy(() -> playerHistoryService.read(1L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("News Not Found");
        verify(playerHistoryRepositoryMock, times(1)).findById(any());

    }

    @Test
    void testReadAll() {
        // given
        PlayerHistory playerHistory = new PlayerHistory(1L, "atacnt", "2022-2023", null, null);
        when(playerHistoryRepositoryMock.findAll()).thenReturn(List.of(playerHistory));
        // when
        playerHistoryService.readAll();
        // then
        verify(playerHistoryRepositoryMock, times(1)).findAll();

    }

    @Test
    void testDelete() {

        // when
        playerHistoryService.delete(1L);
        // then
        verify(playerHistoryRepositoryMock, times(1)).deleteById(1L);

    }

    @Test
    void testMapping() {
        // given //datele care le iei de pe site
        PlayerHistoryDto inputDto = new PlayerHistoryDto(1L, "atacnt", "2022-2023");
        PlayerHistory outputModel = playerHistoryService.dtoToModel(inputDto, 2L);
        assertThat(outputModel.getId()).isEqualTo(2L);
        assertThat(outputModel.getRol()).isEqualTo(inputDto.getRol());
        assertThat(outputModel.getPeriod()).isEqualTo(inputDto.getPeriod());

        //        assertThat(outputModel.getEdition())
        // cand iei din db si pui pe site
        PlayerHistory inputModel = new PlayerHistory(1L, "atacnt", "2022-2023", null, null);
        PlayerHistoryDto outputDto = playerHistoryService.modelToDto(inputModel);
        assertThat(outputDto.getId()).isEqualTo(inputModel.getId());
        assertThat(outputModel.getRol()).isEqualTo(inputDto.getRol());
        assertThat(outputModel.getPeriod()).isEqualTo(inputDto.getPeriod());


    }
}
