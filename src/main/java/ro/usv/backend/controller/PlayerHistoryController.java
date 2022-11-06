package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.usv.backend.dto.PlayerHistoryDto;
import ro.usv.backend.service.PlayerHistoryService;

import java.util.List;

@RestController
@RequestMapping("playerhistory")
public class PlayerHistoryController {
    private final PlayerHistoryService playerHistoryService;

    @Autowired
    public PlayerHistoryController(PlayerHistoryService playerHistoryService) {
        this.playerHistoryService = playerHistoryService;
    }

    @PostMapping
    public PlayerHistoryDto createTeam(@RequestBody PlayerHistoryDto playerHistory) {
        return playerHistoryService.create(playerHistory);
    }

    @GetMapping("/id/{value}")
    public PlayerHistoryDto findById(@RequestParam Long value) {
        return playerHistoryService.read(value);
    }

    @GetMapping
    public List<PlayerHistoryDto> getAll() {
        return playerHistoryService.readAll();
    }

    @PostMapping("/id/{id}")
    public PlayerHistoryDto update(@RequestBody PlayerHistoryDto playerHistory, @RequestParam Long id) {
        return playerHistoryService.update(playerHistory, id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@RequestParam Long id) {
        playerHistoryService.delete(id);
    }

}
