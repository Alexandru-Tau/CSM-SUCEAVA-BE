package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.ChampionshipDto;
import ro.usv.backend.model.Championship;
import ro.usv.backend.repository.ChampionshipRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChampionshipService {
    private final ChampionshipRepository champRepository;

    @Autowired
    public ChampionshipService(ChampionshipRepository champRepository) {
        this.champRepository = champRepository;
    }

    public ChampionshipDto create(ChampionshipDto champDto) {
        Championship champ = new Championship();
        champ.setChampType(champDto.getChampType());
        champ.setEdition(champDto.getEdition());
        champ.setTrophy(champDto.getTrophy());

        Championship response = champRepository.save(champ);

        return new ChampionshipDto(response.getId(), response.getChampType(), response.getEdition(), response.getTrophy());
    }

    public ChampionshipDto update(ChampionshipDto champDto, Long id) {
        Championship champ = new Championship();
        champ.setId(id);
        champ.setChampType(champDto.getChampType());
        champ.setEdition(champDto.getEdition());
        champ.setTrophy(champDto.getTrophy());

        Championship response = champRepository.save(champ);

        return new ChampionshipDto(response.getId(), response.getChampType(), response.getEdition(), response.getTrophy());
    }

    public ChampionshipDto read(Long value) {
        Optional<Championship> champ = champRepository.findById(value);
        if (champ.isPresent()) {
            Championship response = champ.get();
            return new ChampionshipDto(response.getId(), response.getChampType(), response.getEdition(), response.getTrophy());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<ChampionshipDto> readAll() {

        return champRepository.findAll().stream()
                .map(response -> new ChampionshipDto(response.getId(), response.getChampType(),
                        response.getEdition(), response.getTrophy()))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        champRepository.deleteById(id);
    }
}
