package cern.lsa.mapping.example.mappers;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.IdentityHashMap;
import java.util.Map;

public class CycleAvoidingMappingContext {

    private final Map<Object, Object> knownInstances = new IdentityHashMap<Object, Object>();

    /**
     * Gets an instance out of this context if it is already mapped.
     *
     * @param source
     *        given source
     * @param targetType
     *        given target type.
     * @return Returns the resulting type.
     */
    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
        return targetType.cast(knownInstances.get(source));
    }

    /**
     * Puts an instance into the cache, so that it can be remembered to avoid endless mapping.
     *
     * @param source
     *        given source
     * @param target
     *        given target
     */
    @BeforeMapping
    public void storeMappedInstance(Object source, @MappingTarget Object target) {
        knownInstances.put( source, target );
    }
}
