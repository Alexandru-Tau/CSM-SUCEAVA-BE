package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.usv.backend.dto.SponsorsDto;
import ro.usv.backend.service.SponsorsService;

import java.util.List;

@RestController
@RequestMapping("sponsors")
public class SponsorsController {
    private final SponsorsService sponsorsService;

    @Autowired
    public SponsorsController(SponsorsService sponsorsService) {
        this.sponsorsService = sponsorsService;
    }

    @PostMapping
    public SponsorsDto createSponsor(@RequestBody SponsorsDto sponsors) {
        return sponsorsService.create(sponsors);
    }

    @GetMapping("/id/{value}")
    public SponsorsDto findById(@RequestParam Long value) {
        return sponsorsService.read(value);
    }

    @GetMapping
    public List<SponsorsDto> getAll() {
        return sponsorsService.readAll();
    }

    @PostMapping("/id/{id}")
    public SponsorsDto update(@RequestBody SponsorsDto sponsors, @RequestParam Long id) {
        return sponsorsService.update(sponsors, id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@RequestParam Long id) {
        sponsorsService.delete(id);
    }
}
