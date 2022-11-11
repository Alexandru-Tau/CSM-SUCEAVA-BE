package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.ClubDto;
import ro.usv.backend.model.Club;
import ro.usv.backend.repository.ClubRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClubService {
    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public ClubDto create(ClubDto clubDto) {
        Club club = dtoToModel(clubDto, null);
        Club response = clubRepository.save(club);
        return modelToDto(response);
    }

    public ClubDto update(ClubDto clubDto, Long id) {
        Club club = dtoToModel(clubDto, id);
        Club response = clubRepository.save(club);
        return modelToDto(response);
    }

    public ClubDto read(Long value) {
        Optional<Club> club = clubRepository.findById(value);
        if (club.isPresent()) {
            Club response = club.get();
            return modelToDto(response);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<ClubDto> readAll() {

        return clubRepository.findAll().stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        clubRepository.deleteById(id);
    }

    Club dtoToModel(ClubDto dto, Long id) {
        Club model = new Club();
        model.setId(id);
        model.setVision(dto.getVision());
        model.setHistory(dto.getHistory());
        model.setTrophy(dto.getTrophy());
        return model;
    }

    ClubDto modelToDto(Club model) {
        return new ClubDto(model.getId(), model.getVision(), model.getHistory(), model.getTrophy());
    }
}
