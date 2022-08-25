package cern.lsa.mapping.example.dto.cern;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;

public class CollectionLikePerItemSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object object, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (object == null) {
            gen.writeNull();
        } else {
            writeCollectionLike(object, gen, provider);
        }
    }

    @SuppressWarnings("rawtypes")
    private void writeCollectionLike(Object object, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        if (Collection.class.isAssignableFrom(object.getClass())) {
            Collection collection = (Collection) object;
            for (Object collectionElement: collection) {
                provider.defaultSerializeValue(collectionElement, gen);
            }
        } else if (object.getClass().isArray()) {
            int length = Array.getLength(object);
            for (int i = 0; i < length; i ++) {
                Object arrayElement = Array.get(object, i);
                provider.defaultSerializeValue(arrayElement, gen);
            }
        }
        gen.writeEndArray();
    }
}
