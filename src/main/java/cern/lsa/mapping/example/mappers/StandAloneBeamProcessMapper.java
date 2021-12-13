package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.StandAloneBeamProcessImpl;
import cern.lsa.mapping.example.dto.StandAloneBeamProcessDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Mapper(componentModel = "spring", uses = {
        AttributeMapper.class,
        ContextMapper.class,
        SomeImmutableClassMapper.class
})
public abstract class StandAloneBeamProcessMapper {

    @Autowired
    ContextMapper contextMapper;

    @Mappings({
            @Mapping(target = "parentContext", source = "standAloneBeamProcess.parent"),
            @Mapping(target = "someImmutableClassDtoList", source = "standAloneBeamProcess.someImmutableClassList"),
    })
    public abstract StandAloneBeamProcessDto toDto(StandAloneBeamProcessImpl standAloneBeamProcess, @org.mapstruct.Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mappings({
            @Mapping(target = "parent", source = "standAloneBeamProcessDto.parentContext"),
            @Mapping(target = "someImmutableClassList", source = "standAloneBeamProcessDto.someImmutableClassDtoList"),
    })
    public abstract StandAloneBeamProcessImpl fromDto(StandAloneBeamProcessDto standAloneBeamProcessDto, @org.mapstruct.Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @PostConstruct
    private void registerContextInstantiators() {
        contextMapper.registerContextInstantiator(StandAloneBeamProcessImpl.class, this::toDto);
        contextMapper.registerContextInstantiator(StandAloneBeamProcessDto.class, this::fromDto);
    }
}

