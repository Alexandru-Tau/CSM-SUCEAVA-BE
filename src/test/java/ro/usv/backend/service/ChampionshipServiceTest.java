package ro.usv.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.ChampionshipDto;
import ro.usv.backend.model.Championship;
import ro.usv.backend.model.ChampionshipType;
import ro.usv.backend.repository.ChampionshipRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChampionshipServiceTest {
    @Mock
    private ChampionshipRepository championshipRepositoryMock;

    private ChampionshipService championshipService;

    @BeforeEach
    void setup() {
        championshipService = new ChampionshipService(championshipRepositoryMock);
    }

    @Test
    void testCreate() {
        // given
        ChampionshipDto championshipDto = new ChampionshipDto(1L, ChampionshipType.SENIORI, "2022-2023", "Champ");
        Championship championship = new Championship(1L, ChampionshipType.SENIORI, "2022-2023", "Champ", null);
        when(championshipRepositoryMock.save(any())).thenReturn(championship);
        // when
        championshipService.create(championshipDto);
        // then
        verify(championshipRepositoryMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        // given
        ChampionshipDto championshipDto = new ChampionshipDto(1L, ChampionshipType.SENIORI, "2022-2023", "Champ");
        Championship championship = new Championship(1L, ChampionshipType.SENIORI, "2022-2023", "Champ", null);
        when(championshipRepositoryMock.save(any())).thenReturn(championship);
        // when
        championshipService.update(championshipDto, 1L);
        // then
        verify(championshipRepositoryMock, times(1)).save(any());

    }

    @Test
    void testFindById() {
        // given
        Championship championship = new Championship(1L, ChampionshipType.SENIORI, "2022-2023", "Champ", null);
        when(championshipRepositoryMock.findById(any())).thenReturn(Optional.of(championship));
        // when
        championshipService.read(1L);
        // then
        verify(championshipRepositoryMock, times(1)).findById(any());
    }

    @Test
    void testFindByIdNotExisting() {
        // given
        when(championshipRepositoryMock.findById(any())).thenReturn(Optional.empty());
        // when //then
        assertThatThrownBy(() -> championshipService.read(1L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("News Not Found");
        verify(championshipRepositoryMock, times(1)).findById(any());

    }

    @Test
    void testReadAll() {
        // given
        Championship championship = new Championship(1L, ChampionshipType.SENIORI, "2022-2023", "Champ", null);
        when(championshipRepositoryMock.findAll()).thenReturn(List.of(championship));
        // when
        championshipService.readAll();
        // then
        verify(championshipRepositoryMock, times(1)).findAll();

    }

    @Test
    void testDelete() {

        // when
        championshipService.delete(1L);
        // then
        verify(championshipRepositoryMock, times(1)).deleteById(1L);

    }

    @Test
    void testMapping() {
        // given //datele care le iei de pe site
        ChampionshipDto inputDto = new ChampionshipDto(1L, ChampionshipType.SENIORI, "2022-2023", "Champ");
        Championship outputModel = championshipService.dtoToModel(inputDto, 2L);
        assertThat(outputModel.getId()).isEqualTo(2L);
        assertThat(outputModel.getTrophy()).isEqualTo(inputDto.getTrophy());
        assertThat(outputModel.getChampType()).isEqualTo(inputDto.getChampType());
        assertThat(outputModel.getEdition()).isEqualTo(inputDto.getEdition());
        //        assertThat(outputModel.getEdition())
        // cand iei din db si pui pe site
        Championship inputModel = new Championship(1L, ChampionshipType.SENIORI, "2022-2013", "Ch", null);
        ChampionshipDto outputDto = championshipService.modelToDto(inputModel);
        assertThat(outputDto.getId()).isEqualTo(inputModel.getId());
        assertThat(outputDto.getTrophy()).isEqualTo(inputModel.getTrophy());
        assertThat(outputDto.getChampType()).isEqualTo(inputModel.getChampType());
        assertThat(outputDto.getEdition()).isEqualTo(inputModel.getEdition());


    }
}
