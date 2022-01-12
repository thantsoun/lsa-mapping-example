package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.ImmutableParentClass;
import cern.lsa.mapping.example.dto.ImmutableParentClassDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ImmutableParentClassMapper {

    ImmutableParentClassDto toDto(ImmutableParentClass immutableParentClass);
    ImmutableParentClass fromDto(ImmutableParentClassDto immutableParentClassDto);
}

