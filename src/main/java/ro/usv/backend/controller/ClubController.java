package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.usv.backend.dto.ClubDto;
import ro.usv.backend.service.ClubService;

import java.util.List;

@RestController
@RequestMapping("club")
public class ClubController {
    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    public ClubDto createClub(@RequestBody ClubDto club) {
        return clubService.create(club);
    }

    @GetMapping("/id/{value}")
    public ClubDto findById(@RequestParam Long value) {
        return clubService.read(value);
    }

    @GetMapping
    public List<ClubDto> getAll() {
        return clubService.readAll();
    }

    @PostMapping("/id/{id}")
    public ClubDto update(@RequestBody ClubDto club, @RequestParam Long id) {
        return clubService.update(club, id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@RequestParam Long id) {
        clubService.delete(id);
    }
}
