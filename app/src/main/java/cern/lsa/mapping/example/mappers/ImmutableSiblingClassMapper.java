package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.ImmutableSiblingClass;
import cern.lsa.mapping.example.dto.ImmutableSiblingClassDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ImmutableSiblingClassMapper {

//    ImmutableSiblingClassDto toDto(ImmutableSiblingClass immutableSiblingClass);
//    ImmutableSiblingClass fromDto(ImmutableSiblingClassDto immutableSiblingClassDto);

}

