package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.MatchDto;
import ro.usv.backend.model.Match;
import ro.usv.backend.repository.MatchRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public MatchDto create(MatchDto matchDto) {
        Match match = dtoToModel(matchDto, null);
        Match response = matchRepository.save(match);
        return modelToDto(response);
    }


    public MatchDto update(MatchDto matchDto, Long id) {
        Match match = dtoToModel(matchDto, id);
        Match response = matchRepository.save(match);
        return modelToDto(response);
    }

    public MatchDto read(Long value) {
        Optional<Match> match = matchRepository.findById(value);
        if (match.isPresent()) {
            Match response = match.get();
            return modelToDto(response);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<MatchDto> readAll() {

        return matchRepository.findAll().stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        matchRepository.deleteById(id);
    }

    Match dtoToModel(MatchDto dto, Long id) {
        Match match = new Match();
        match.setId(id);
        match.setMatchDateTime(dto.getMatchDateTime());
        match.setIsFinished(dto.getIsFinished());
        match.setMatchType(dto.getMatchType());
        match.setLocation(dto.getLocation());
        match.setLiveLink(dto.getLiveLink());
        match.setFinalScore(dto.getFinalScore());
        return match;

    }

    MatchDto modelToDto(Match model) {
        return new MatchDto(model.getId(), model.getMatchDateTime(), model.getIsFinished(),
                model.getMatchType(), model.getLocation(), model.getLiveLink(), model.getFinalScore());

    }

}
