package cern.lsa.mapping.example.TestEnums.json;

import cern.lsa.mapping.example.dto.cern.CollectionLikePerItemSerializer;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;


public class EnumSerializerModifier extends BeanSerializerModifier {

    private final EnumsInJsonEnforcer enumsInJsonEnforcer = new EnumsInJsonEnforcer();
    private final CollectionLikePerItemSerializer collectionLikePerItemSerializer = new CollectionLikePerItemSerializer();

    @Override
    @SuppressWarnings("unchecked")
    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        Class<?> clazz = beanDesc.getBeanClass();
        if (clazz.isEnum()) {
            enumsInJsonEnforcer.ensureIsJsonable((Class<? extends Enum<?>>) clazz);
        }
        return serializer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonSerializer<?> modifyEnumSerializer(SerializationConfig config, JavaType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        Class<?> clazz = beanDesc.getBeanClass();
        if (clazz.isEnum()) {
            enumsInJsonEnforcer.ensureIsJsonable((Class<? extends Enum<?>>) clazz);
        }
        return serializer;
    }

    @Override
    public JsonSerializer<?> modifyArraySerializer(SerializationConfig config, ArrayType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        return collectionLikePerItemSerializer;
    }

    @Override
    public JsonSerializer<?> modifyCollectionSerializer(SerializationConfig config, CollectionType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        return collectionLikePerItemSerializer;
    }

}
