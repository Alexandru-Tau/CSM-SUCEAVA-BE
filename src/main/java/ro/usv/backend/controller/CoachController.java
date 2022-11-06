package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.usv.backend.dto.CoachDto;
import ro.usv.backend.service.CoachService;

import java.util.List;

@RestController
@RequestMapping("coach")
public class CoachController {
    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping
    public CoachDto createCoach(@RequestBody CoachDto coach) {
        return coachService.create(coach);
    }

    @GetMapping("/id/{value}")
    public CoachDto findById(@RequestParam Long value) {
        return coachService.read(value);
    }

    @GetMapping
    public List<CoachDto> getAll() {
        return coachService.readAll();
    }

    @PostMapping("/id/{id}")
    public CoachDto update(@RequestBody CoachDto coach, @RequestParam Long id) {
        return coachService.update(coach, id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@RequestParam Long id) {
        coachService.delete(id);
    }
}
