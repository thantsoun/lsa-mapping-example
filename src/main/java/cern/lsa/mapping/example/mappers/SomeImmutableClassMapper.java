package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.SomeImmutableClass;
import cern.lsa.mapping.example.dto.SomeImmutableClassDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SomeImmutableClassMapper {
    @Mappings({
            @Mapping(expression = "java(source.attr1())", target = "attr1"),
            @Mapping(expression = "java(source.attr2())", target = "attr2"),
    })
    SomeImmutableClassDto toDto(SomeImmutableClass source);
    SomeImmutableClass fromDto(SomeImmutableClassDto source);
}

