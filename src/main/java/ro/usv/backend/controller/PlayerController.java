package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ro.usv.backend.dto.NewsDto;
import ro.usv.backend.dto.PlayerDto;
import ro.usv.backend.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public PlayerDto createPlayer(@RequestBody PlayerDto player) {
        return playerService.create(player);
    }

    @GetMapping("/id/{value}")
    public PlayerDto findById(@RequestParam Long value) {
        return playerService.read(value);
    }

    @GetMapping
    public List<PlayerDto> getAll() {
        return playerService.readAll();
    }

    @PostMapping("/id/{id}")
    public PlayerDto update(@RequestBody PlayerDto player, @RequestParam Long id) {
        return playerService.update(player, id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@RequestParam Long id) {
        playerService.delete(id);
    }
}
