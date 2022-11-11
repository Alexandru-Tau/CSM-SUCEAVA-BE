package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.PlayerDto;
import ro.usv.backend.model.Player;
import ro.usv.backend.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerDto create(PlayerDto playerDto) {
        Player player = dtoToModel(playerDto, null);
        Player response = playerRepository.save(player);
        return modelToDto(response);
    }

    public PlayerDto update(PlayerDto playerDto, Long id) {
        Player player = dtoToModel(playerDto, id);
        Player response = playerRepository.save(player);
        return modelToDto(response);
    }

    public PlayerDto read(Long value) {
        Optional<Player> player = playerRepository.findById(value);
        if (player.isPresent()) {
            Player response = player.get();
            return modelToDto(response);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<PlayerDto> readAll() {

        return playerRepository.findAll().stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        playerRepository.deleteById(id);
    }

    Player dtoToModel(PlayerDto dto, Long id) {
        Player player = new Player();
        player.setId(id);
        player.setName(dto.getName());
        player.setFirstName(dto.getFirstName());
        player.setNationality(dto.getNationality());
        player.setPosition(dto.getPosition());
        player.setBirthDate(dto.getBirthDate());
        player.setHeight(dto.getHeight());
        player.setDescription(dto.getDescription());
        return player;
    }

    PlayerDto modelToDto(Player model) {
        return new PlayerDto(model.getId(), model.getName(), model.getFirstName(),
                model.getNationality(), model.getPosition(), model.getBirthDate(),
                model.getHeight(), model.getDescription());

    }
}
