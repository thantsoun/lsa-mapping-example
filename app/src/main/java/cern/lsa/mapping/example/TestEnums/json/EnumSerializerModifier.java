package cern.lsa.mapping.example.TestEnums.json;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;


public class EnumSerializerModifier extends BeanSerializerModifier {

    private final EnumsInJsonEnforcer enumsInJsonEnforcer = new EnumsInJsonEnforcer();

    @Override
    @SuppressWarnings("unchecked")
    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        Class<?> clazz = beanDesc.getBeanClass();
        if (clazz.isEnum()) {
            enumsInJsonEnforcer.ensureIsJsonable((Class<? extends Enum<?>>) clazz);
        }
        return serializer;
    }

}
