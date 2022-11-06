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
        Match match = new Match();
        match.setMatchDateTime(matchDto.getMatchDateTime());
        match.setIsFinished(matchDto.getIsFinished());
        match.setMatchType(matchDto.getMatchType());
        match.setLocation(matchDto.getLocation());
        match.setLiveLink(matchDto.getLiveLink());
        match.setFinalScore(matchDto.getFinalScore());
        Match response = matchRepository.save(match);

        return new MatchDto(response.getId(), matchDto.getMatchDateTime(), matchDto.getIsFinished(),
                matchDto.getMatchType(), response.getLocation(), matchDto.getLiveLink(), match.getFinalScore());
    }


    public MatchDto update(MatchDto matchDto, Long id) {
        Match match = new Match();
        match.setId(id);
        match.setMatchDateTime(matchDto.getMatchDateTime());
        match.setIsFinished(matchDto.getIsFinished());
        match.setMatchType(matchDto.getMatchType());
        match.setLocation(matchDto.getLocation());
        match.setLiveLink(matchDto.getLiveLink());
        match.setFinalScore(matchDto.getFinalScore());
        Match response = matchRepository.save(match);

        return new MatchDto(response.getId(), response.getMatchDateTime(), response.getIsFinished(),
                response.getMatchType(), response.getLocation(), response.getLiveLink(), response.getFinalScore());
    }

    public MatchDto read(Long value) {
        Optional<Match> match = matchRepository.findById(value);
        if (match.isPresent()) {
            Match response = match.get();
            return new MatchDto(response.getId(), response.getMatchDateTime(), response.getIsFinished(),
                    response.getMatchType(), response.getLocation(), response.getLiveLink(), response.getFinalScore());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<MatchDto> readAll() {

        return matchRepository.findAll().stream()
                .map(response -> new MatchDto(response.getId(), response.getMatchDateTime(), response.getIsFinished(),
                        response.getMatchType(), response.getLocation(), response.getLiveLink(), response.getFinalScore()))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        matchRepository.deleteById(id);
    }

}
