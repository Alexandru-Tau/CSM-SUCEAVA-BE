package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.usv.backend.dto.SponsorsDto;
import ro.usv.backend.model.Sponsors;
import ro.usv.backend.repository.SponsorsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SponsorsService {
    private final SponsorsRepository sponsorsRepository;

    @Autowired
    public SponsorsService(SponsorsRepository sponsorsRepository) {
        this.sponsorsRepository = sponsorsRepository;
    }

    public SponsorsDto create(SponsorsDto sponsorsDto) {
        Sponsors sponsors = dtoToModel(sponsorsDto, null);
        Sponsors response = sponsorsRepository.save(sponsors);
        return modelToDto(response);
    }

    public SponsorsDto update(SponsorsDto sponsorsDto, Long id) {
        Sponsors sponsors = dtoToModel(sponsorsDto, id);
        Sponsors response = sponsorsRepository.save(sponsors);
        return modelToDto(response);
    }

    public SponsorsDto read(Long value) {
        Optional<Sponsors> sponsors = sponsorsRepository.findById(value);
        if (sponsors.isPresent()) {
            Sponsors response = sponsors.get();
            return modelToDto(response);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<SponsorsDto> readAll() {

        return sponsorsRepository.findAll().stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        sponsorsRepository.deleteById(id);
    }

    Sponsors dtoToModel(SponsorsDto dto, Long id) {
        Sponsors sponsors = new Sponsors();
        sponsors.setId(id);
        sponsors.setName(dto.getName());
        sponsors.setLogo(dto.getLogo());
        sponsors.setLinkSite(dto.getLinkSite());
        sponsors.setEdition(dto.getEdition());

        return sponsors;
    }

    SponsorsDto modelToDto(Sponsors model) {
        return new SponsorsDto(model.getId(), model.getName(), model.getLogo(), model.getLinkSite(), model.getEdition());

    }

}
