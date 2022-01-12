package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.BeamProcessImpl;
import cern.lsa.mapping.example.dto.BeamProcessDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Mapper(uses = {
            ContextMapper.class
        },
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public abstract class BeamProcessMapper {

    @Autowired
    MappersHelperService mappersHelperService;
    @Autowired
    ContextMapper contextMapper;

    @Mappings({
            @Mapping(target = "name", expression = "java(mappersHelperService.enhanceName(beamProcess.getName()))"),
            @Mapping(target = "parentContext", source = "beamProcess.parent")
    })
    public abstract BeamProcessDto toDto(BeamProcessImpl beamProcess, @org.mapstruct.Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mappings({
            @Mapping(target = "name", expression = "java(mappersHelperService.deEnhanceName(beamProcessDto.getName()))"),
            @Mapping(target = "parent", source = "beamProcessDto.parentContext"),
            @Mapping(target = "category", source = "beamProcessDto.category", defaultExpression = "java(mappersHelperService.getDefaultCategory())")
    })
    public abstract BeamProcessImpl fromDto(BeamProcessDto beamProcessDto, @org.mapstruct.Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @PostConstruct
    private void registerContextInstantiators() {
        contextMapper.registerContextInstantiator(BeamProcessImpl.class, this::toDto);
        contextMapper.registerContextInstantiator(BeamProcessDto.class, this::fromDto);
    }
}

