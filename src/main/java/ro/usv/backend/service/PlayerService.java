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
        Player player = new Player();
        player.setName(playerDto.getName());
        player.setFirstName(playerDto.getFirstName());
        player.setNationality(playerDto.getNationality());
        player.setPosition(playerDto.getPosition());
        player.setBirthDate(playerDto.getBirthDate());
        player.setHeight(playerDto.getHeight());
        player.setDescription(playerDto.getDescription());

        Player response = playerRepository.save(player);

        return new PlayerDto(response.getId(), response.getName(), response.getFirstName(),
                response.getNationality(), response.getPosition(), response.getBirthDate(),
                response.getHeight(), response.getDescription());
    }

    public PlayerDto update(PlayerDto playerDto, Long id) {

        Player player = new Player();
        player.setId(id);
        player.setName(playerDto.getName());
        player.setFirstName(playerDto.getFirstName());
        player.setNationality(playerDto.getNationality());
        player.setPosition(playerDto.getPosition());
        player.setBirthDate(playerDto.getBirthDate());
        player.setHeight(playerDto.getHeight());
        player.setDescription(playerDto.getDescription());

        Player response = playerRepository.save(player);

        return new PlayerDto(response.getId(), response.getName(), response.getFirstName(),
                response.getNationality(), response.getPosition(), response.getBirthDate(),
                response.getHeight(), response.getDescription());
    }

    public PlayerDto read(Long value) {
        Optional<Player> player = playerRepository.findById(value);
        if (player.isPresent()) {
            Player response = player.get();
            return new PlayerDto(response.getId(), response.getName(), response.getFirstName(),
                    response.getNationality(), response.getPosition(), response.getBirthDate(),
                    response.getHeight(), response.getDescription());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<PlayerDto> readAll() {

        return playerRepository.findAll().stream()
                .map(response -> new PlayerDto(response.getId(), response.getName(), response.getFirstName(),
                        response.getNationality(), response.getPosition(), response.getBirthDate(),
                        response.getHeight(), response.getDescription()))
                .collect(Collectors.toList());
    }
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
