package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.usv.backend.dto.ChampionshipDto;
import ro.usv.backend.service.ChampionshipService;

import java.util.List;

@RestController
@RequestMapping("champ")
public class ChampionshipController {
    private final ChampionshipService champService;

    @Autowired
    public ChampionshipController(ChampionshipService champService) {
        this.champService = champService;
    }

    @PostMapping
    public ChampionshipDto createChamp(@RequestBody ChampionshipDto champ) {
        return champService.create(champ);
    }

    @GetMapping("/id/{value}")
    public ChampionshipDto findById(@RequestParam Long value) {
        return champService.read(value);
    }

    @GetMapping
    public List<ChampionshipDto> getAll() {
        return champService.readAll();
    }

    @PostMapping("/id/{id}")
    public ChampionshipDto update(@RequestBody ChampionshipDto champ, @RequestParam Long id) {
        return champService.update(champ, id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@RequestParam Long id) {
        champService.delete(id);
    }
}
