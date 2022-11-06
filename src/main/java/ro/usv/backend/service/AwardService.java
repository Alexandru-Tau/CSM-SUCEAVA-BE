package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.AwardDto;
import ro.usv.backend.model.Award;
import ro.usv.backend.repository.AwardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AwardService {
    private final AwardRepository awardRepository;

    @Autowired
    public AwardService(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }

    public AwardDto create(AwardDto awardDto) {
        Award award = new Award();
        award.setDateAward(awardDto.getDateAward());
        award.setTypeAward(awardDto.getTypeAward());

        Award response = awardRepository.save(award);

        return new AwardDto(response.getId(), response.getTypeAward(), response.getDateAward());
    }

    public AwardDto update(AwardDto awardDto, Long id) {
        Award award = new Award();
        award.setId(id);
        award.setDateAward(awardDto.getDateAward());
        award.setTypeAward(awardDto.getTypeAward());

        Award response = awardRepository.save(award);

        return new AwardDto(response.getId(), response.getTypeAward(), response.getDateAward());
    }

    public AwardDto read(Long value) {
        Optional<Award> award = awardRepository.findById(value);
        if (award.isPresent()) {
            Award response = award.get();
            return new AwardDto(response.getId(), response.getTypeAward(), response.getDateAward());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<AwardDto> readAll() {

        return awardRepository.findAll().stream()
                .map(response -> new AwardDto(response.getId(), response.getTypeAward(), response.getDateAward()))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        awardRepository.deleteById(id);
    }
}
