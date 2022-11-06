package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.usv.backend.dto.MatchDto;
import ro.usv.backend.service.MatchService;

import java.util.List;

@RestController
@RequestMapping("match")
public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public MatchDto createMatch(@RequestBody MatchDto match) {
        return matchService.create(match);
    }

    @GetMapping("/id/{value}")
    public MatchDto findById(@RequestParam Long value) {
        return matchService.read(value);
    }

    @GetMapping
    public List<MatchDto> getAll() {
        return matchService.readAll();
    }

    @PostMapping("/id/{id}")
    public MatchDto update(@RequestBody MatchDto match, @RequestParam Long id) {
        return matchService.update(match, id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@RequestParam Long id) {
        matchService.delete(id);
    }
}
