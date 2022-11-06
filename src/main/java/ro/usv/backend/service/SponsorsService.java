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
        Sponsors sponsors = new Sponsors();
        sponsors.setName(sponsorsDto.getName());
        sponsors.setLogo(sponsorsDto.getLogo());
        sponsors.setLinkSite(sponsorsDto.getLinkSite());
        sponsors.setEdition(sponsorsDto.getEdition());

        Sponsors response = sponsorsRepository.save(sponsors);

        return new SponsorsDto(response.getId(), response.getName(), response.getLogo(), response.getLinkSite(), response.getEdition());
    }

    public SponsorsDto update(SponsorsDto sponsorsDto, Long id) {
        Sponsors sponsors = new Sponsors();
        sponsors.setId(id);
        sponsors.setName(sponsorsDto.getName());
        sponsors.setLogo(sponsorsDto.getLogo());
        sponsors.setLinkSite(sponsorsDto.getLinkSite());
        sponsors.setEdition(sponsorsDto.getEdition());

        Sponsors response = sponsorsRepository.save(sponsors);

        return new SponsorsDto(response.getId(), response.getName(), response.getLogo(), response.getLinkSite(), response.getEdition());
    }

    public SponsorsDto read(Long value) {
        Optional<Sponsors> sponsors = sponsorsRepository.findById(value);
        if (sponsors.isPresent()) {
            Sponsors response = sponsors.get();
            return new SponsorsDto(response.getId(), response.getName(), response.getLogo(), response.getLinkSite(), response.getEdition());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "News Not Found");
        }

    }

    public List<SponsorsDto> readAll() {

        return sponsorsRepository.findAll().stream()
                .map(response -> new SponsorsDto(response.getId(), response.getName(),
                        response.getLogo(), response.getLinkSite(), response.getEdition()))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        sponsorsRepository.deleteById(id);
    }

}
