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
        Club club = new Club();
        club.setVision(clubDto.getVision());
        club.setHistory(clubDto.getHistory());
        club.setTrophy(clubDto.getTrophy());

        Club response = clubRepository.save(club);

        return new ClubDto(response.getId(), response.getVision(), response.getHistory(), response.getTrophy());
    }

    public ClubDto update(ClubDto clubDto, Long id) {
        Club club = new Club();
        club.setId(id);
        club.setVision(clubDto.getVision());
        club.setHistory(clubDto.getHistory());
        club.setTrophy(clubDto.getTrophy());

        Club response = clubRepository.save(club);

        return new ClubDto(response.getId(), response.getVision(), response.getHistory(), response.getTrophy());
    }

    public ClubDto read(Long value) {
        Optional<Club> club = clubRepository.findById(value);
        if (club.isPresent()) {
            Club response = club.get();
            return new ClubDto(response.getId(), response.getVision(), response.getHistory(), response.getTrophy());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<ClubDto> readAll() {

        return clubRepository.findAll().stream()
                .map(response -> new ClubDto(response.getId(), response.getVision(),
                        response.getHistory(), response.getTrophy()))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        clubRepository.deleteById(id);
    }
}
