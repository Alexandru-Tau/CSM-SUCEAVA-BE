package ro.usv.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.TeamDto;
import ro.usv.backend.model.Team;
import ro.usv.backend.model.TeamType;
import ro.usv.backend.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {
    @Mock
    private TeamRepository teamRepositoryMock;

    private TeamService teamService;

    @BeforeEach
    void setup() {
        teamService = new TeamService(teamRepositoryMock);
    }

    @Test
    void testCreate() {
        // given
        TeamDto teamDto = new TeamDto(1L, "Andrei", TeamType.JUNIORI);
        Team team = new Team(1L, "Andrei", TeamType.JUNIORI, null, null, null, null);
        when(teamRepositoryMock.save(any())).thenReturn(team);
        // when
        teamService.create(teamDto);
        // then
        verify(teamRepositoryMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        // given
        TeamDto teamDto = new TeamDto(1L, "Andrei", TeamType.JUNIORI);
        Team team = new Team(1L, "Andrei", TeamType.JUNIORI, null, null, null, null);
        when(teamRepositoryMock.save(any())).thenReturn(team);
        // when
        teamService.update(teamDto, 1L);
        // then
        verify(teamRepositoryMock, times(1)).save(any());

    }

    @Test
    void testFindById() {
        // given
        Team team = new Team(1L, "Andrei", TeamType.JUNIORI, null, null, null, null);
        when(teamRepositoryMock.findById(any())).thenReturn(Optional.of(team));
        // when
        teamService.read(1L);
        // then
        verify(teamRepositoryMock, times(1)).findById(any());
    }

    @Test
    void testFindByIdNotExisting() {
        // given
        when(teamRepositoryMock.findById(any())).thenReturn(Optional.empty());
        // when //then
        assertThatThrownBy(() -> teamService.read(1L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("News Not Found");
        verify(teamRepositoryMock, times(1)).findById(any());

    }

    @Test
    void testReadAll() {
        // given
        Team team = new Team(1L, "Andrei", TeamType.JUNIORI, null, null, null, null);
        when(teamRepositoryMock.findAll()).thenReturn(List.of(team));
        // when
        teamService.readAll();
        // then
        verify(teamRepositoryMock, times(1)).findAll();

    }

    @Test
    void testDelete() {

        // when
        teamService.delete(1L);
        // then
        verify(teamRepositoryMock, times(1)).deleteById(1L);

    }

    @Test
    void testMapping() {
        // given //datele care le iei de pe site
        TeamDto inputDto = new TeamDto(1L, "Andrei", TeamType.JUNIORI);
        Team outputModel = teamService.dtoToModel(inputDto, 2L);
        assertThat(outputModel.getId()).isEqualTo(2L);
        assertThat(outputModel.getLogo()).isEqualTo(inputDto.getLogo());
        assertThat(outputModel.getTeamType()).isEqualTo(inputDto.getTeamType());


        //        assertThat(outputModel.getEdition())
        // cand iei din db si pui pe site
        Team inputModel = new Team(1L, "Andrei", TeamType.JUNIORI, null, null, null, null);
        TeamDto outputDto = teamService.modelToDto(inputModel);
        assertThat(outputDto.getId()).isEqualTo(inputModel.getId());
        assertThat(outputDto.getLogo()).isEqualTo(inputModel.getLogo());
        assertThat(outputDto.getTeamType()).isEqualTo(inputModel.getTeamType());


    }
}
