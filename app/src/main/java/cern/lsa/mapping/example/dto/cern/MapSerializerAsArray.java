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
        provider.defaultSerializeValue(entry.getKey(), gen);
        provider.defaultSerializeValue(entry.getValue(), gen);
        gen.writeEndArray();
    }

}
