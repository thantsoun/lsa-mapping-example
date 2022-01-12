package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.ImmutableChildClass;
import cern.lsa.mapping.example.dto.ImmutableChildClassDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = ImmutableSiblingClassMapper.class, componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ImmutableChildClassMapper {

    ImmutableChildClassDto toDto(ImmutableChildClass immutableChildClass);
    ImmutableChildClass fromDto(ImmutableChildClassDto immutableChildClassDto);

}

