package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.*;
import cern.lsa.mapping.example.dto.AttributeDto;
import cern.lsa.mapping.example.dto.BeamProcessDto;
import cern.lsa.mapping.example.dto.HandcraftedClassDto;
import cern.lsa.mapping.example.dto.StandAloneBeamProcessDto;
import org.springframework.stereotype.Service;

@Service
public class MapperFacade {

    private final AttributeMapper attributeMapper;
    private final BeamProcessMapper beamProcessMapper;
    private final StandAloneBeamProcessMapper standAloneBeamProcessMapper;
    private final HandcraftedClassMapper handcraftedClassMapper;

    public MapperFacade(AttributeMapper attributeMapper, BeamProcessMapper beamProcessMapper, StandAloneBeamProcessMapper standAloneBeamProcessMapper, HandcraftedClassMapper handcraftedClassMapper) {
        this.attributeMapper = attributeMapper;
        this.beamProcessMapper = beamProcessMapper;
        this.standAloneBeamProcessMapper = standAloneBeamProcessMapper;
        this.handcraftedClassMapper = handcraftedClassMapper;
    }

    public StandAloneBeamProcess fromStandAloneBeamProcessDto(StandAloneBeamProcessDto standAloneBeamProcessDto) {
        return standAloneBeamProcessMapper.fromDto(standAloneBeamProcessDto, new CycleAvoidingMappingContext());
    }

    public BeamProcess fromBeamProcessDto(BeamProcessDto beamProcessDto) {
        return beamProcessMapper.fromDto(beamProcessDto, new CycleAvoidingMappingContext());
    }

    public StandAloneBeamProcessDto toStandAloneBeamProcessDto(StandAloneBeamProcessImpl standALoneBM) {
        return standAloneBeamProcessMapper.toDto(standALoneBM, new CycleAvoidingMappingContext());
    }

    public BeamProcessDto toBeamProcessDto(BeamProcessImpl beamProcess) {
        return beamProcessMapper.toDto(beamProcess, new CycleAvoidingMappingContext());
    }

    public AttributeDto toAttributeDto(Attribute attribute) {
        return attributeMapper.toDto(attribute);
    }

    public Attribute fromAttributeDto(AttributeDto attributeDto) {
        return attributeMapper.fromDto(attributeDto);
    }

    public HandcraftedClassDto toHandCraftedDto(HandcraftedClass handcraftedClass) {
        return handcraftedClassMapper.toDto(handcraftedClass);
    }
}
