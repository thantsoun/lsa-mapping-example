package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.HandcraftedClass;
import cern.lsa.mapping.example.dto.HandcraftedClassDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(uses = {
        ImmutableChildClassMapper.class,
        ImmutableParentClassMapper.class
},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public abstract class HandcraftedClassMapper {

    @Autowired
    ImmutableChildClassMapper immutableChildClassMapper;
    @Autowired
    ImmutableParentClassMapper immutableParentClassMapper;

    public abstract HandcraftedClassDto toDto(HandcraftedClass handcraftedClass);
    public abstract HandcraftedClass fromDto(HandcraftedClassDto handcraftedClassDto);

}

