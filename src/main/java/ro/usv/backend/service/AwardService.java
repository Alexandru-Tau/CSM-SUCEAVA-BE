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
        Award award = dtoToModel(awardDto, null);
        Award response = awardRepository.save(award);
        return modelToAward(response);
    }

    public AwardDto update(AwardDto awardDto, Long id) {
        Award award = dtoToModel(awardDto, id);
        Award response = awardRepository.save(award);
        return modelToAward(response);
    }

    public AwardDto readById(Long value) {
        Optional<Award> award = awardRepository.findById(value);
        if (award.isPresent()) {
            Award response = award.get();
            return modelToAward(response);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<AwardDto> readAll() {

        return awardRepository.findAll().stream()
                .map(this::modelToAward)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        awardRepository.deleteById(id);
    }

    Award dtoToModel(AwardDto dto, Long id) {
        Award model = new Award();
        model.setId(id);
        model.setDateAward(dto.getDateAward());
        model.setTypeAward(dto.getTypeAward());
        model.setId(id);
        return model;
    }

    AwardDto modelToAward(Award model) {
        return new AwardDto(model.getId(), model.getTypeAward(), model.getDateAward());
    }
}
