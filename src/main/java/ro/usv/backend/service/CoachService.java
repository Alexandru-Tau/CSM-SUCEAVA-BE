package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.CoachDto;
import ro.usv.backend.model.Coach;
import ro.usv.backend.repository.CoachRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoachService {
    private final CoachRepository coachRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public CoachDto create(CoachDto coachDto) {
        Coach coach= dtoToModel(coachDto,null);
        Coach response = coachRepository.save(coach);
       return modelToDto(response);
    }

    public CoachDto update(CoachDto coachDto, Long id) {
        Coach coach= dtoToModel(coachDto,id);
        Coach response = coachRepository.save(coach);
        return modelToDto(response);
    }

    public CoachDto read(Long value) {
        Optional<Coach> coach = coachRepository.findById(value);
        if (coach.isPresent()) {
            Coach response = coach.get();
            return modelToDto(response);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<CoachDto> readAll() {

        return coachRepository.findAll().stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        coachRepository.deleteById(id);
    }

    Coach dtoToModel(CoachDto dto, Long id) {

        Coach model = new Coach();
        model.setId(id);
        model.setName(dto.getName());
        model.setFirstName(dto.getFirstName());
        model.setNationality(dto.getNationality());
        model.setPosition(dto.getPosition());
        model.setBirthDate(dto.getBirthDate());
        model.setDescription(dto.getDescription());
        return model;
    }

    CoachDto modelToDto(Coach model) {
        return new CoachDto(model.getId(), model.getName(), model.getFirstName(),
                model.getNationality(), model.getPosition(), model.getBirthDate(),
                model.getDescription());
    }
}
