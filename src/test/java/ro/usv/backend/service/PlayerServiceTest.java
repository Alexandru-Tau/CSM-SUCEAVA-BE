package ro.usv.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.PlayerDto;
import ro.usv.backend.model.Player;
import ro.usv.backend.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepositoryMock;

    private PlayerService playerService;

    @BeforeEach
    void setup() {
        playerService = new PlayerService(playerRepositoryMock);
    }

    @Test
    void testCreate() {
        // given
        PlayerDto playerDto = new PlayerDto(1L, "Andrei", "T", "Romana", "atacant", null, 182, "sata");
        Player player = new Player(1L, "Andrei", "T", "Romana", "atacant", null, 182, "sata", null, null);
        when(playerRepositoryMock.save(any())).thenReturn(player);
        // when
        playerService.create(playerDto);
        // then
        verify(playerRepositoryMock, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        // given
        PlayerDto playerDto = new PlayerDto(1L, "Andrei", "T", "Romana", "atacant", null, 182, "sata");
        Player player = new Player(1L, "Andrei", "T", "Romana", "atacant", null, 182, "sata", null, null);
        when(playerRepositoryMock.save(any())).thenReturn(player);
        // when
        playerService.update(playerDto, 1L);
        // then
        verify(playerRepositoryMock, times(1)).save(any());

    }

    @Test
    void testFindById() {
        // given
        Player player = new Player(1L, "Andrei", "T", "Romana", "atacant", null, 182, "sata", null, null);
        when(playerRepositoryMock.findById(any())).thenReturn(Optional.of(player));
        // when
        playerService.read(1L);
        // then
        verify(playerRepositoryMock, times(1)).findById(any());
    }

    @Test
    void testFindByIdNotExisting() {
        // given
        when(playerRepositoryMock.findById(any())).thenReturn(Optional.empty());
        // when //then
        assertThatThrownBy(() -> playerService.read(1L))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("News Not Found");
        verify(playerRepositoryMock, times(1)).findById(any());

    }

    @Test
    void testReadAll() {
        // given
        Player player = new Player(1L, "Andrei", "T", "Romana", "atacant", null, 182, "sata", null, null);
        when(playerRepositoryMock.findAll()).thenReturn(List.of(player));
        // when
        playerService.readAll();
        // then
        verify(playerRepositoryMock, times(1)).findAll();

    }

    @Test
    void testDelete() {

        // when
        playerService.delete(1L);
        // then
        verify(playerRepositoryMock, times(1)).deleteById(1L);

    }

    @Test
    void testMapping() {
        // given //datele care le iei de pe site
        PlayerDto inputDto = new PlayerDto(1L, "Andrei", "T", "Romana", "atacant", null, 182, "sata");
        Player outputModel = playerService.dtoToModel(inputDto, 2L);
        assertThat(outputModel.getId()).isEqualTo(2L);
        assertThat(outputModel.getName()).isEqualTo(inputDto.getName());
        assertThat(outputModel.getFirstName()).isEqualTo(inputDto.getFirstName());
        assertThat(outputModel.getNationality()).isEqualTo(inputDto.getNationality());
        assertThat(outputModel.getHeight()).isEqualTo(inputDto.getHeight());
        assertThat(outputModel.getPosition()).isEqualTo(inputDto.getPosition());
        assertThat(outputModel.getBirthDate()).isEqualTo(inputDto.getBirthDate());
        assertThat(outputModel.getDescription()).isEqualTo(inputDto.getDescription());

        //        assertThat(outputModel.getEdition())
        // cand iei din db si pui pe site
        Player inputModel = new Player(1L, "Andrei", "T", "Romana", "atacant", null, 182, "sata", null, null);
        PlayerDto outputDto = playerService.modelToDto(inputModel);
        assertThat(outputDto.getId()).isEqualTo(inputModel.getId());
        assertThat(outputDto.getName()).isEqualTo(inputModel.getName());
        assertThat(outputDto.getFirstName()).isEqualTo(inputModel.getFirstName());
        assertThat(outputDto.getNationality()).isEqualTo(inputModel.getNationality());
        assertThat(outputDto.getHeight()).isEqualTo(inputModel.getHeight());
        assertThat(outputDto.getPosition()).isEqualTo(inputModel.getPosition());
        assertThat(outputDto.getBirthDate()).isEqualTo(inputModel.getBirthDate());
        assertThat(outputDto.getDescription()).isEqualTo(inputModel.getDescription());


    }
}
