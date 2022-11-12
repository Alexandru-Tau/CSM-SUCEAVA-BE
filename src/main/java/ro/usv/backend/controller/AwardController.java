package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.usv.backend.dto.AwardDto;
import ro.usv.backend.service.AwardService;

import java.util.List;

@RestController
@RequestMapping("award")
public class AwardController {
    private final AwardService awardService;

    @Autowired
    public AwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @PostMapping
    public AwardDto create(@RequestBody AwardDto award) {
        return awardService.create(award);
    }

    @GetMapping("/id/{value}")
    public AwardDto readById(@RequestParam Long value) {
        return awardService.readById(value);
    }

    @GetMapping
    public List<AwardDto> readAll() {
        return awardService.readAll();
    }

    @PostMapping("/id/{id}")
    public AwardDto update(@RequestBody AwardDto award, @RequestParam Long id) {
        return awardService.update(award, id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@RequestParam Long id) {
        awardService.delete(id);
    }

    @PostMapping("/history/{historyId}")
    public AwardDto awardForHistory(@RequestBody AwardDto award, @RequestParam Long historyId) {
        return awardService.createAwardForHistory(award,historyId);
    }

}
