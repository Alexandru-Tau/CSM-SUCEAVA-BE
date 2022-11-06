package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.PlayerHistoryDto;
import ro.usv.backend.model.PlayerHistory;
import ro.usv.backend.repository.PlayerHistoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerHistoryService {
    private final PlayerHistoryRepository playerHistoryRepository;

    @Autowired
    public PlayerHistoryService(PlayerHistoryRepository playerHistoryRepository) {
        this.playerHistoryRepository = playerHistoryRepository;
    }

    public PlayerHistoryDto create(PlayerHistoryDto playerHistoryDto) {
        PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setRol(playerHistoryDto.getRol());
        playerHistory.setPeriod(playerHistoryDto.getPeriod());

        PlayerHistory response = playerHistoryRepository.save(playerHistory);

        return new PlayerHistoryDto(response.getId(), response.getRol(), response.getPeriod());
    }

    public PlayerHistoryDto update(PlayerHistoryDto playerHistoryDto, Long id) {
        PlayerHistory playerHistory = new PlayerHistory();
        playerHistory.setId(id);
        playerHistory.setRol(playerHistoryDto.getRol());
        playerHistory.setPeriod(playerHistoryDto.getPeriod());

        PlayerHistory response = playerHistoryRepository.save(playerHistory);

        return new PlayerHistoryDto(response.getId(), response.getRol(), response.getPeriod());
    }

    public PlayerHistoryDto read(Long value) {
        Optional<PlayerHistory> playerHistory = playerHistoryRepository.findById(value);
        if (playerHistory.isPresent()) {
            PlayerHistory response = playerHistory.get();
            return new PlayerHistoryDto(response.getId(), response.getRol(), response.getPeriod());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<PlayerHistoryDto> readAll() {

        return playerHistoryRepository.findAll().stream()
                .map(response -> new PlayerHistoryDto(response.getId(), response.getRol(), response.getPeriod()))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        playerHistoryRepository.deleteById(id);
    }

}
