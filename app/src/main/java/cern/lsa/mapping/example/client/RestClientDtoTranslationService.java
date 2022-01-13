package cern.lsa.mapping.example.client;

import cern.lsa.mapping.example.domain.Attribute;
import cern.lsa.mapping.example.domain.BeamProcess;
import cern.lsa.mapping.example.domain.HandcraftedClass;
import cern.lsa.mapping.example.domain.StandAloneBeamProcess;
import cern.lsa.mapping.example.dto.AttributeDto;
import cern.lsa.mapping.example.dto.BeamProcessDto;
import cern.lsa.mapping.example.dto.HandcraftedClassDto;
import cern.lsa.mapping.example.dto.StandAloneBeamProcessDto;
import cern.lsa.mapping.example.mappers.MapperFacade;
import cern.lsa.mapping.example.referenced.ReferencedCircularImmutable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RestClientDtoTranslationService {

    private final RestClient restClient;
    private final MapperFacade mapperFacade;

    public RestClientDtoTranslationService(RestClient restClient, MapperFacade mapperFacade) {
        this.restClient = restClient;
        this.mapperFacade = mapperFacade;
    }

    public Collection<Attribute> getAttributes() {
        Collection<AttributeDto> attributes = restClient.getAttributes();
        return attributes.stream().map(mapperFacade::fromAttributeDto).collect(Collectors.toSet());
    }

    public BeamProcess getBMs() {
        BeamProcessDto beamProcessDto = restClient.getBeamProcesses();
        return mapperFacade.fromBeamProcessDto(beamProcessDto);
    }

    public StandAloneBeamProcess getStandAloneBMs() {
        StandAloneBeamProcessDto standAloneBeamProcessDto = restClient.getStandAloneBeamProcesses();
        return mapperFacade.fromStandAloneBeamProcessDto(standAloneBeamProcessDto);
    }

    public HandcraftedClass getHandCraftedClass() {
        HandcraftedClassDto handcraftedClassDto = restClient.getHandCraftedClass();
        return mapperFacade.fromHandCraftedDto(handcraftedClassDto);
    }


    public ReferencedCircularImmutable getReferencedCircularImmutable() {
        return restClient.getReferencedCircularImmutable().toImmutable();
    }

}
