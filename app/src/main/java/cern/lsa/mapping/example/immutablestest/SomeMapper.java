package cern.lsa.mapping.example.immutablestest;

import org.mapstruct.Mapper;

@Mapper
public interface SomeMapper {
    SomeClassDto toDto(SomeClass someClass);
    SomeImmutableClassDto toDto(SomeImmutableClass someClass);
    SomeClass toDto(SomeClassDto someClass);
    SomeImmutableClass toDto(SomeImmutableClassDto someClass);
}

