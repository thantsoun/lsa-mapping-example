package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.gsi.GsiBeamProcessImpl;
import cern.lsa.mapping.example.dto.gsi.GsiBeamProcessDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Mapper(uses = {
            ContextMapper.class
        },
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public abstract class GsiBeamProcessMapper {

    @Autowired
    ContextMapper contextMapper;

    @Mapping(target = "parentContext", source = "beamProcess.parent")
    public abstract GsiBeamProcessDto toDto(GsiBeamProcessImpl beamProcess, @org.mapstruct.Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "parent", source = "beamProcessDto.parentContext")
    public abstract GsiBeamProcessImpl fromDto(GsiBeamProcessDto beamProcessDto, @org.mapstruct.Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @PostConstruct
    private void registerContextInstantiators() {
        contextMapper.registerContextInstantiator(GsiBeamProcessImpl.class, this::toDto);
        contextMapper.registerContextInstantiator(GsiBeamProcessDto.class, this::fromDto);
    }
}

