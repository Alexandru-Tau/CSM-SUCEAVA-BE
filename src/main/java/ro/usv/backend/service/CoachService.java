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
        Coach coach = new Coach();
        coach.setName(coachDto.getName());
        coach.setFirstName(coachDto.getFirstName());
        coach.setNationality(coachDto.getNationality());
        coach.setPosition(coachDto.getPosition());
        coach.setBirthDate(coachDto.getBirthDate());
        coach.setDescription(coachDto.getDescription());

        Coach response = coachRepository.save(coach);

        return new CoachDto(response.getId(), response.getName(), response.getFirstName(),
                response.getNationality(), response.getPosition(), response.getBirthDate(),
                response.getDescription());
    }

    public CoachDto update(CoachDto coachDto, Long id) {

        Coach coach = new Coach();
        coach.setId(id);
        coach.setName(coachDto.getName());
        coach.setFirstName(coachDto.getFirstName());
        coach.setNationality(coachDto.getNationality());
        coach.setPosition(coachDto.getPosition());
        coach.setBirthDate(coachDto.getBirthDate());
        coach.setDescription(coachDto.getDescription());

        Coach response = coachRepository.save(coach);

        return new CoachDto(response.getId(), response.getName(), response.getFirstName(),
                response.getNationality(), response.getPosition(), response.getBirthDate(),
                response.getDescription());
    }

    public CoachDto read(Long value) {
        Optional<Coach> coach = coachRepository.findById(value);
        if (coach.isPresent()) {
            Coach response = coach.get();
            return new CoachDto(response.getId(), response.getName(), response.getFirstName(),
                    response.getNationality(), response.getPosition(), response.getBirthDate(),
                    response.getDescription());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<CoachDto> readAll() {

        return coachRepository.findAll().stream()
                .map(response -> new CoachDto(response.getId(), response.getName(), response.getFirstName(),
                        response.getNationality(), response.getPosition(), response.getBirthDate(),
                        response.getDescription()))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        coachRepository.deleteById(id);
    }
}
