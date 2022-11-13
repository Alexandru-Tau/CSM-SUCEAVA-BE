package ro.usv.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.ClubDto;
import ro.usv.backend.model.Club;
import ro.usv.backend.repository.ClubRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClubServiceTest {
    @Mock
    private ClubRepository clubRepositoryMock;

    private ClubService clubService;

    @BeforeEach
    void setup() {
        clubService = new ClubService(clubRepositoryMock);
    }

    @Test
    void testCreate() {
        // given
        ClubDto clubDto = new ClubDto(1L, "SENIORI", "2022-2023", "Champ");
        Club club = new Club(1L, "SENIORI", "2022-2023", "Champ", null, null);
        when(clubRepositoryMock.save(any())).thenReturn(club);
        // when
        clubService.create(clubDto);
        // then
        verify(clubRepositoryMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        // given
        ClubDto clubDto = new ClubDto(1L, "SENIORI", "2022-2023", "Champ");
        Club club = new Club(1L, "SENIORI", "2022-2023", "Champ", null, null);
        when(clubRepositoryMock.save(any())).thenReturn(club);
        // when
        clubService.update(clubDto, 1L);
        // then
        verify(clubRepositoryMock, times(1)).save(any());

    }

    @Test
    void testFindById() {
        // given
        Club club = new Club(1L, "SENIORI", "2022-2023", "Champ", null, null);
        when(clubRepositoryMock.findById(any())).thenReturn(Optional.of(club));
        // when
        clubService.read(1L);
        // then
        verify(clubRepositoryMock, times(1)).findById(any());
    }

    @Test
    void testFindByIdNotExisting() {
        // given
        when(clubRepositoryMock.findById(any())).thenReturn(Optional.empty());
        // when //then
        assertThatThrownBy(() -> clubService.read(1L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("News Not Found");
        verify(clubRepositoryMock, times(1)).findById(any());

    }

    @Test
    void testReadAll() {
        // given
        Club club = new Club(1L, "SENIORI", "2022-2023", "Champ", null, null);
        when(clubRepositoryMock.findAll()).thenReturn(List.of(club));
        // when
        clubService.readAll();
        // then
        verify(clubRepositoryMock, times(1)).findAll();

    }

    @Test
    void testDelete() {

        // when
        clubService.delete(1L);
        // then
        verify(clubRepositoryMock, times(1)).deleteById(1L);

    }

    @Test
    void testMapping() {
        // given //datele care le iei de pe site
        ClubDto inputDto = new ClubDto(1L, "SENIORI", "2022-2023", "Champ");
        Club outputModel = clubService.dtoToModel(inputDto, 2L);
        assertThat(outputModel.getId()).isEqualTo(2L);
        assertThat(outputModel.getTrophy()).isEqualTo(inputDto.getTrophy());
        assertThat(outputModel.getHistory()).isEqualTo(inputDto.getHistory());
        assertThat(outputModel.getVision()).isEqualTo(inputDto.getVision());
        //        assertThat(outputModel.getEdition())
        // cand iei din db si pui pe site
        Club inputModel = new Club(1L, "SENIORI", "2022-2023", "Champ", null, null);
        ClubDto outputDto = clubService.modelToDto(inputModel);
        assertThat(outputDto.getId()).isEqualTo(inputModel.getId());
        assertThat(outputDto.getTrophy()).isEqualTo(inputModel.getTrophy());
        assertThat(outputDto.getHistory()).isEqualTo(inputModel.getHistory());
        assertThat(outputDto.getVision()).isEqualTo(inputModel.getVision());


    }
}
