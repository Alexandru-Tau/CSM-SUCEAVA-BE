package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.TeamDto;
import ro.usv.backend.model.Team;
import ro.usv.backend.repository.TeamRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamDto create(TeamDto teamDto) {
        Team team = new Team();
        team.setLogo(teamDto.getLogo());
        team.setTeamType(teamDto.getTeamType());

        Team response = teamRepository.save(team);

        return new TeamDto(response.getId(), response.getLogo(), response.getTeamType());
    }

    public TeamDto update(TeamDto teamDto, Long id) {
        Team team = new Team();
        team.setId(id);
        team.setLogo(teamDto.getLogo());
        team.setTeamType(teamDto.getTeamType());

        Team response = teamRepository.save(team);

        return new TeamDto(response.getId(), response.getLogo(), response.getTeamType());
    }

    public TeamDto read(Long value) {
        Optional<Team> team = teamRepository.findById(value);
        if (team.isPresent()) {
            Team response = team.get();
            return new TeamDto(response.getId(), response.getLogo(), response.getTeamType());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<TeamDto> readAll() {

        return teamRepository.findAll().stream()
                .map(response -> new TeamDto(response.getId(), response.getLogo(), response.getTeamType()))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
