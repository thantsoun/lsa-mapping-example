package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.DefaultSomeImmutableClass;
import cern.lsa.mapping.example.domain.SomeImmutableClass;
import cern.lsa.mapping.example.dto.DefaultSomeImmutableClassDto;
import cern.lsa.mapping.example.dto.SomeImmutableClassDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SomeImmutableClassMapper {
    DefaultSomeImmutableClassDto toDto(SomeImmutableClass source);
    DefaultSomeImmutableClass fromDto(SomeImmutableClassDto source);
}

