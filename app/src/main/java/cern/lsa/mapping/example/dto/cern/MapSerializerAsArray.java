package cern.lsa.mapping.example.dto.cern;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class MapSerializerAsArray extends JsonSerializer<Map<?, ?>> {

    @Override
    public void serialize(Map<?, ?> value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeStartArray();
        for (Map.Entry<?, ?> entry : value.entrySet()) {
            writeMapEntry(gen, provider, entry);
        }
        gen.writeEndArray();
    }

    private void writeMapEntry(JsonGenerator gen, SerializerProvider provider, Map.Entry<?, ?> entry) throws IOException {
        gen.writeStartArray();
        writeObject(gen, provider, entry.getKey());
        writeObject(gen, provider, entry.getValue());
        gen.writeEndArray();
    }

    private void writeObject(JsonGenerator gen, SerializerProvider provider, Object obj) throws IOException {
        if (obj == null) {
            gen.writeNull();
        } else {
            writeNonNullObj(gen, provider, obj);
        }
    }

    @SuppressWarnings("rawtypes")
    private void writeNonNullObj(JsonGenerator gen, SerializerProvider provider, Object obj) throws IOException {
        Class<?> objClass = obj.getClass();
        if (Collection.class.isAssignableFrom(objClass)) {
            writeCollectionObject(gen, provider, (Collection) obj);
        } else {
            provider.defaultSerializeValue(obj, gen);
        }
    }

    private void writeCollectionObject(JsonGenerator gen, SerializerProvider provider, Collection<?> obj) throws IOException {
        gen.writeStartArray();
        for (Object objInCollection : obj) {
            writeObject(gen, provider, objInCollection);
        }
        gen.writeEndArray();
    }
}
