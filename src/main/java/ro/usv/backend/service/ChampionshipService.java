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
        Championship champ = dtoToModel(champDto, null);
        Championship response = champRepository.save(champ);
        return modelToDto(response);


    }

    public ChampionshipDto update(ChampionshipDto champDto, Long id) {
        Championship champ = dtoToModel(champDto, id);
        Championship response = champRepository.save(champ);
        return modelToDto(response);

    }

    public ChampionshipDto read(Long value) {
        Optional<Championship> champ = champRepository.findById(value);
        if (champ.isPresent()) {
            Championship response = champ.get();
            return modelToDto(response);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<ChampionshipDto> readAll() {

        return champRepository.findAll().stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        champRepository.deleteById(id);
    }

    Championship dtoToModel(ChampionshipDto dto, Long id) {
        Championship model = new Championship();
        model.setChampType(dto.getChampType());
        model.setEdition(dto.getEdition());
        model.setTrophy(dto.getTrophy());
        model.setId(id);
        return model;
    }

    ChampionshipDto modelToDto(Championship model) {
        return new ChampionshipDto(model.getId(), model.getChampType(), model.getEdition(), model.getTrophy());
    }
}
