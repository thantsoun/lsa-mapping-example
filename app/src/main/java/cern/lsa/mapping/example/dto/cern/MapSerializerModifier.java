package cern.lsa.mapping.example.dto.cern;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.type.MapType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MapSerializerModifier extends BeanSerializerModifier {

    private final MapSerializerAsArray mapSerializerAsArray = new MapSerializerAsArray();
    private final Set<Class<?>> nonComplexKeyClasses = new HashSet<>(Arrays.asList(Character.class, String.class, Short.class, Integer.class, Long.class, Float.class, Double.class));

    @Override
    public JsonSerializer<?> modifyMapSerializer(SerializationConfig config, MapType valueType, BeanDescription beanDesc, JsonSerializer serializer) {
        Class<?> keyClass = valueType.getKeyType().getRawClass();
        return nonComplexKeyClasses.contains(keyClass) ? serializer : mapSerializerAsArray;
    }
}
