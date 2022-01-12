package cern.lsa.mapping.example.mappers;

import cern.lsa.mapping.example.domain.Context;
import cern.lsa.mapping.example.dto.ContextDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class ContextMapper {

    private final Map<String, BiFunction> instantiators = new HashMap<>();

    <S, T> void registerContextInstantiator(Class<S> contextClass, BiFunction<S, CycleAvoidingMappingContext, T> contextMapper) {
        instantiators.put(contextClass.getName(), contextMapper);
    }

    public ContextDto toContextDto(Context context, @org.mapstruct.Context CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        if (context == null) {
            return null;
        }
        String sourceClassName = context.getClass().getName();
        if (instantiators.containsKey(sourceClassName)) {
            return (ContextDto) instantiators.get(sourceClassName).apply(context, cycleAvoidingMappingContext);
        }
        throw new UnsupportedOperationException("Cannot map class " + sourceClassName + " to " + ContextDto.class.getName());
    }

    public Context toContext(ContextDto contextDto, @org.mapstruct.Context CycleAvoidingMappingContext cycleAvoidingMappingContext) {
        if (contextDto == null) {
            return null;
        }
        String sourceClassName = contextDto.getClass().getName();
        if (instantiators.containsKey(sourceClassName)) {
            return (Context) instantiators.get(sourceClassName).apply(contextDto, cycleAvoidingMappingContext);
        }
        throw new UnsupportedOperationException("Cannot map class " + sourceClassName + " to " + Context.class.getName());
    }
}

