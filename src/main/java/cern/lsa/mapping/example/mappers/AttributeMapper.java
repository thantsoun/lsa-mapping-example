package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.Attribute;
import cern.lsa.mapping.example.dto.AttributeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttributeMapper {
    AttributeDto toDto(Attribute attr);
    Attribute fromDto(AttributeDto attrDto);
}

