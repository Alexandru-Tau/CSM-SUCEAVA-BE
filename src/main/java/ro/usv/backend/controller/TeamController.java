package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.usv.backend.dto.TeamDto;
import ro.usv.backend.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public TeamDto createTeam(@RequestBody TeamDto team) {
        return teamService.create(team);
    }

    @GetMapping("/id/{value}")
    public TeamDto findById(@RequestParam Long value) {
        return teamService.read(value);
    }

    @GetMapping
    public List<TeamDto> getAll() {
        return teamService.readAll();
    }

    @PostMapping("/id/{id}")
    public TeamDto update(@RequestBody TeamDto team, @RequestParam Long id) {
        return teamService.update(team, id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@RequestParam Long id) {
        teamService.delete(id);
    }
}
