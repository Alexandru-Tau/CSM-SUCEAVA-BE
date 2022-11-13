package ro.usv.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.MatchDto;
import ro.usv.backend.model.Match;
import ro.usv.backend.model.MatchType;
import ro.usv.backend.repository.MatchRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {
    @Mock
    private MatchRepository matchRepositoryMock;

    private MatchService matchService;

    @BeforeEach
    void setup() {
        matchService = new MatchService(matchRepositoryMock);
    }

    @Test
    void testCreate() {
        // given
        MatchDto matchDto = new MatchDto(1L, null, false, MatchType.AMICAL, "Suceava", null, "null");
        Match match = new Match(1L, null, false, MatchType.AMICAL, "Suceava", null, "null", null, null);
        when(matchRepositoryMock.save(any())).thenReturn(match);
        // when
        matchService.create(matchDto);
        // then
        verify(matchRepositoryMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        // given
        MatchDto matchDto = new MatchDto(1L, null, false, MatchType.AMICAL, "Suceava", null, "null");
        Match match = new Match(1L, null, false, MatchType.AMICAL, "Suceava", null, "null", null, null);
        when(matchRepositoryMock.save(any())).thenReturn(match);
        // when
        matchService.update(matchDto, 1L);
        // then
        verify(matchRepositoryMock, times(1)).save(any());

    }

    @Test
    void testFindById() {
        // given
        Match match = new Match(1L, null, false, MatchType.AMICAL, "Suceava", null, "null", null, null);
        when(matchRepositoryMock.findById(any())).thenReturn(Optional.of(match));
        // when
        matchService.read(1L);
        // then
        verify(matchRepositoryMock, times(1)).findById(any());
    }

    @Test
    void testFindByIdNotExisting() {
        // given
        when(matchRepositoryMock.findById(any())).thenReturn(Optional.empty());
        // when //then
        assertThatThrownBy(() -> matchService.read(1L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("News Not Found");
        verify(matchRepositoryMock, times(1)).findById(any());

    }

    @Test
    void testReadAll() {
        // given
        Match match = new Match(1L, null, false, MatchType.AMICAL, "Suceava", null, "null", null, null);
        when(matchRepositoryMock.findAll()).thenReturn(List.of(match));
        // when
        matchService.readAll();
        // then
        verify(matchRepositoryMock, times(1)).findAll();

    }

    @Test
    void testDelete() {

        // when
        matchService.delete(1L);
        // then
        verify(matchRepositoryMock, times(1)).deleteById(1L);

    }

    @Test
    void testMapping() {
        // given //datele care le iei de pe site
        MatchDto inputDto = new MatchDto(1L, null, false, MatchType.AMICAL, "Suceava", null, "null");
        Match outputModel = matchService.dtoToModel(inputDto, 2L);
        assertThat(outputModel.getId()).isEqualTo(2L);
        assertThat(outputModel.getMatchDateTime()).isEqualTo(inputDto.getMatchDateTime());
        assertThat(outputModel.getIsFinished()).isEqualTo(inputDto.getIsFinished());
        assertThat(outputModel.getMatchType()).isEqualTo(inputDto.getMatchType());
        assertThat(outputModel.getLocation()).isEqualTo(inputDto.getLocation());
        assertThat(outputModel.getLiveLink()).isEqualTo(inputDto.getLiveLink());
        assertThat(outputModel.getFinalScore()).isEqualTo(inputDto.getFinalScore());

        //        assertThat(outputModel.getEdition())
        // cand iei din db si pui pe site
        Match inputModel = new Match(1L, null, false, MatchType.AMICAL, "Suceava", null, "null", null, null);
        MatchDto outputDto = matchService.modelToDto(inputModel);
        assertThat(outputDto.getId()).isEqualTo(inputModel.getId());
        assertThat(outputDto.getMatchDateTime()).isEqualTo(inputModel.getMatchDateTime());
        assertThat(outputDto.getIsFinished()).isEqualTo(inputModel.getIsFinished());
        assertThat(outputDto.getMatchType()).isEqualTo(inputModel.getMatchType());
        assertThat(outputDto.getLocation()).isEqualTo(inputModel.getLocation());
        assertThat(outputDto.getLiveLink()).isEqualTo(inputModel.getLiveLink());
        assertThat(outputDto.getFinalScore()).isEqualTo(inputModel.getFinalScore());


    }
}
