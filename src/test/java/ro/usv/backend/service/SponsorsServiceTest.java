package ro.usv.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.SponsorsDto;
import ro.usv.backend.model.Sponsors;
import ro.usv.backend.repository.SponsorsRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SponsorsServiceTest {
    @Mock
    private SponsorsRepository sponsorsRepositoryMock;

    private SponsorsService sponsorsService;

    @BeforeEach
    void setup() {
        sponsorsService = new SponsorsService(sponsorsRepositoryMock);
    }

    @Test
    void testCreate() {
        // given
        SponsorsDto sponsorsDto = new SponsorsDto(1L, "Andrei", "T", null, "2022-2023");
        Sponsors sponsors = new Sponsors(1L, "Andrei", "T", null, "2022-2023", null);
        when(sponsorsRepositoryMock.save(any())).thenReturn(sponsors);
        // when
        sponsorsService.create(sponsorsDto);
        // then
        verify(sponsorsRepositoryMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        // given
        SponsorsDto sponsorsDto = new SponsorsDto(1L, "Andrei", "T", null, "2022-2023");
        Sponsors sponsors = new Sponsors(1L, "Andrei", "T", null, "2022-2023", null);
        when(sponsorsRepositoryMock.save(any())).thenReturn(sponsors);
        // when
        sponsorsService.update(sponsorsDto, 1L);
        // then
        verify(sponsorsRepositoryMock, times(1)).save(any());

    }

    @Test
    void testFindById() {
        // given
        Sponsors sponsors = new Sponsors(1L, "Andrei", "T", null, "2022-2023", null);
        when(sponsorsRepositoryMock.findById(any())).thenReturn(Optional.of(sponsors));
        // when
        sponsorsService.read(1L);
        // then
        verify(sponsorsRepositoryMock, times(1)).findById(any());
    }

    @Test
    void testFindByIdNotExisting() {
        // given
        when(sponsorsRepositoryMock.findById(any())).thenReturn(Optional.empty());
        // when //then
        assertThatThrownBy(() -> sponsorsService.read(1L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("News Not Found");
        verify(sponsorsRepositoryMock, times(1)).findById(any());

    }

    @Test
    void testReadAll() {
        // given
        Sponsors sponsors = new Sponsors(1L, "Andrei", "T", null, "2022-2023", null);
        when(sponsorsRepositoryMock.findAll()).thenReturn(List.of(sponsors));
        // when
        sponsorsService.readAll();
        // then
        verify(sponsorsRepositoryMock, times(1)).findAll();

    }

    @Test
    void testDelete() {

        // when
        sponsorsService.delete(1L);
        // then
        verify(sponsorsRepositoryMock, times(1)).deleteById(1L);

    }

    @Test
    void testMapping() {
        // given //datele care le iei de pe site
        SponsorsDto inputDto = new SponsorsDto(1L, "Andrei", "T", null, "2022-2023");
        Sponsors outputModel = sponsorsService.dtoToModel(inputDto, 2L);
        assertThat(outputModel.getId()).isEqualTo(2L);
        assertThat(outputModel.getName()).isEqualTo(inputDto.getName());
        assertThat(outputModel.getEdition()).isEqualTo(inputDto.getEdition());
        assertThat(outputModel.getLogo()).isEqualTo(inputDto.getLogo());
        assertThat(outputModel.getLinkSite()).isEqualTo(inputDto.getLinkSite());


        //        assertThat(outputModel.getEdition())
        // cand iei din db si pui pe site
        Sponsors inputModel = new Sponsors(1L, "Andrei", "T", null, "2022-2023", null);
        SponsorsDto outputDto = sponsorsService.modelToDto(inputModel);
        assertThat(outputDto.getId()).isEqualTo(inputModel.getId());
        assertThat(outputDto.getName()).isEqualTo(inputModel.getName());
        assertThat(outputDto.getEdition()).isEqualTo(inputModel.getEdition());
        assertThat(outputDto.getLogo()).isEqualTo(inputModel.getLogo());
        assertThat(outputDto.getLinkSite()).isEqualTo(inputModel.getLinkSite());


    }
}
