package cern.lsa.mapping.example.TestEnums.json;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;

public class EnumDeserializerModifier extends BeanDeserializerModifier {

    private final EnumsInJsonEnforcer enumsInJsonEnforcer = new EnumsInJsonEnforcer();

    @Override
    @SuppressWarnings("unchecked")
    public JsonDeserializer<?> modifyEnumDeserializer(DeserializationConfig config, JavaType type, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        Class<?> clazz = beanDesc.getBeanClass();
        if (clazz.isEnum()) {
            Class<? extends Enum<?>> asEnum = (Class<? extends Enum<?>>) clazz;
            enumsInJsonEnforcer.ensureIsJsonable(asEnum);
            return new EnumDeserializer(asEnum);
        }
        return deserializer;
    }
}
