package ro.usv.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.AwardDto;
import ro.usv.backend.dto.PlayerHistoryDto;
import ro.usv.backend.model.Award;
import ro.usv.backend.model.PlayerHistory;
import ro.usv.backend.repository.AwardRepository;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AwardServiceTest {
    @Mock
    private AwardRepository awardRepositoryMock;

    @Mock
    private PlayerHistoryService playerHistoryServiceMock;

    private AwardService awardService;

    @BeforeEach
    void setup() {
        awardService = new AwardService(awardRepositoryMock, playerHistoryServiceMock);
    }

    @Test
    void testCreate() {
        // given
        AwardDto awardDto = new AwardDto(1L, "Golgheter", Date.from(Instant.now()));
        Award award = new Award(1L, "Golgheter", Date.from(Instant.now()), null);
        when(awardRepositoryMock.save(any())).thenReturn(award);
        // when
        awardService.create(awardDto);
        // then
        verify(awardRepositoryMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        // given
        AwardDto awardDto = new AwardDto(1L, "Golgheter", Date.from(Instant.now()));
        Award award = new Award(1L, "Golgheter", Date.from(Instant.now()), null);
        when(awardRepositoryMock.save(any())).thenReturn(award);
        // when
        awardService.update(awardDto, 1L);
        // then
        verify(awardRepositoryMock, times(1)).save(any());

    }

    @Test
    void testFindById() {
        // given
        Award award = new Award(1L, "Golgheter", Date.from(Instant.now()), null);
        when(awardRepositoryMock.findById(any())).thenReturn(Optional.of(award));
        // when
        awardService.readById(1L);
        // then
        verify(awardRepositoryMock, times(1)).findById(any());

    }

    @Test
    void testFindByIdNotExisting() {
        // given
        when(awardRepositoryMock.findById(any())).thenReturn(Optional.empty());
        // when //then
        assertThatThrownBy(() -> awardService.readById(1L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("News Not Found");
        verify(awardRepositoryMock, times(1)).findById(any());

    }

    @Test
    void testReadAll() {
        // given
        Award award = new Award(1L, "Golgheter", Date.from(Instant.now()), null);
        when(awardRepositoryMock.findAll()).thenReturn(List.of(award));
        // when
        awardService.readAll();
        // then
        verify(awardRepositoryMock, times(1)).findAll();

    }

    @Test
    void testDelete() {

        // when
        awardService.delete(1L);
        // then
        verify(awardRepositoryMock, times(1)).deleteById(1L);

    }

    @Test
    void testMapping() {
        // given
        AwardDto inputDto = new AwardDto(1L, "asist", Date.from(Instant.now()));
        Award inputModel = new Award(1L, "Golgheter", Date.from(Instant.now()), null);
        // when
        AwardDto outputDto = awardService.modelToAward(inputModel);
        Award outputModel = awardService.dtoToModel(inputDto, 2L);
        // then
        assertThat(outputDto.getId()).isEqualTo(1L);
        assertThat(outputDto.getTypeAward()).isEqualTo(inputModel.getTypeAward());
        assertThat(outputModel.getId()).isEqualTo(2L);
        assertThat(outputModel.getTypeAward()).isEqualTo(inputDto.getTypeAward());
    }

    @Test
    void testCreateAwardForHistory() {
        // given
        AwardDto awardDto = new AwardDto(1L, "Golgheter", Date.from(Instant.now()));
        Award award = new Award(1L, "Golgheter", Date.from(Instant.now()), null);
        Long historyId = 1L;
        when(awardRepositoryMock.save(any())).thenReturn(award);
        when(playerHistoryServiceMock.dtoToModel(any(), any())).thenReturn(new PlayerHistory());
        when(playerHistoryServiceMock.read(anyLong())).thenReturn(new PlayerHistoryDto());
        // when
        awardService.createAwardForHistory(awardDto, historyId);
        // then
        verify(awardRepositoryMock, times(1)).save(any());
    }


}
