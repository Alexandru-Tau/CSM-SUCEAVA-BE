package ro.usv.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.CoachDto;
import ro.usv.backend.model.Coach;
import ro.usv.backend.repository.CoachRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CoachServiceTest {
    @Mock
    private CoachRepository coachRepositoryMock;

    private CoachService coachService;

    @BeforeEach
    void setup() {
        coachService = new CoachService(coachRepositoryMock);
    }

    @Test
    void testCreate() {
        // given
        CoachDto coachDto = new CoachDto(1L, "Andrei", "Pont", "Germana", "Principal", null, "sad");
        Coach coach = new Coach(1L, "Andrei", "Pont", "Germana", "Principal", null, "sad", null, null);
        when(coachRepositoryMock.save(any())).thenReturn(coach);
        // when
        coachService.create(coachDto);
        // then
        verify(coachRepositoryMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        // given
        CoachDto coachDto = new CoachDto(1L, "Andrei", "Pont", "Germana", "Principal", null, "sad");
        Coach coach = new Coach(1L, "Andrei", "Pont", "Germana", "Principal", null, "sad", null, null);
        when(coachRepositoryMock.save(any())).thenReturn(coach);
        // when
        coachService.update(coachDto, 1L);
        // then
        verify(coachRepositoryMock, times(1)).save(any());

    }

    @Test
    void testFindById() {
        // given
        Coach coach = new Coach(1L, "Andrei", "Pont", "Germana", "Principal", null, "sad", null, null);
        when(coachRepositoryMock.findById(any())).thenReturn(Optional.of(coach));
        // when
        coachService.read(1L);
        // then
        verify(coachRepositoryMock, times(1)).findById(any());
    }

    @Test
    void testFindByIdNotExisting() {
        // given
        when(coachRepositoryMock.findById(any())).thenReturn(Optional.empty());
        // when //then
        assertThatThrownBy(() -> coachService.read(1L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("News Not Found");
        verify(coachRepositoryMock, times(1)).findById(any());

    }

    @Test
    void testReadAll() {
        // given
        Coach coach = new Coach(1L, "Andrei", "Pont", "Germana", "Principal", null, "sad", null, null);
        when(coachRepositoryMock.findAll()).thenReturn(List.of(coach));
        // when
        coachService.readAll();
        // then
        verify(coachRepositoryMock, times(1)).findAll();

    }

    @Test
    void testDelete() {

        // when
        coachService.delete(1L);
        // then
        verify(coachRepositoryMock, times(1)).deleteById(1L);

    }

    @Test
    void testMapping() {
        // given //datele care le iei de pe site
        CoachDto inputDto = new CoachDto(1L, "Andrei", "Pont", "Germana", "Principal", null, "sad");
        Coach outputModel = coachService.dtoToModel(inputDto, 2L);
        assertThat(outputModel.getId()).isEqualTo(2L);
        assertThat(outputModel.getName()).isEqualTo(inputDto.getName());
        assertThat(outputModel.getFirstName()).isEqualTo(inputDto.getFirstName());
        assertThat(outputModel.getNationality()).isEqualTo(inputDto.getNationality());
        assertThat(outputModel.getPosition()).isEqualTo(inputDto.getPosition());
        assertThat(outputModel.getDescription()).isEqualTo(inputDto.getDescription());
        assertThat(outputModel.getBirthDate()).isEqualTo(inputDto.getBirthDate());

        //        assertThat(outputModel.getEdition())
        // cand iei din db si pui pe site
        Coach inputModel = new Coach(1L, "Andrei", "Pont", "Germana", "Principal", null, "sad", null, null);
        CoachDto outputDto = coachService.modelToDto(inputModel);
        assertThat(outputDto.getId()).isEqualTo(inputModel.getId());
        assertThat(outputDto.getName()).isEqualTo(inputModel.getName());
        assertThat(outputDto.getFirstName()).isEqualTo(inputModel.getFirstName());
        assertThat(outputDto.getNationality()).isEqualTo(inputModel.getNationality());
        assertThat(outputDto.getPosition()).isEqualTo(inputModel.getPosition());
        assertThat(outputDto.getDescription()).isEqualTo(inputModel.getDescription());
        assertThat(outputDto.getBirthDate()).isEqualTo(inputModel.getBirthDate());


    }
}
