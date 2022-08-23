package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ObjectWithListMap {

    @JsonSerialize(using = MyGenericJsonSerializer.class)
//    @JsonDeserialize(using = MyJsonDeserializer.class)
    private final Map<MapKey, List<MapValue>> map;
    private final String someRandomString;

    @JsonCreator
    public ObjectWithListMap(@JsonProperty("map") Map<MapKey, List<MapValue>> map, @JsonProperty("someRandomString") String someRandomString) {
        this.map = map;
        this.someRandomString = someRandomString;
    }

    public Map<MapKey, List<MapValue>> getMap() {
        return map;
    }

    public String getSomeRandomString() {
        return someRandomString;
    }

    public static class MyGenericJsonSerializer<K, V> extends JsonSerializer<Map<K, Collection<V>>> {

        @Override
        public void serialize(Map<K, Collection<V>> value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeStartArray();
            for (Map.Entry<K, Collection<V>> entry: value.entrySet()) {
                writeMapEntry(gen, provider, entry);
            }
            gen.writeEndArray();
        }

        private void writeMapEntry(JsonGenerator gen, SerializerProvider provider, Map.Entry<K, Collection<V>> entry) throws IOException {
            gen.writeStartArray();
            provider.defaultSerializeValue(entry.getKey(), gen);
            gen.writeStartArray();
            for (V value: entry.getValue()) {
                provider.defaultSerializeValue(value, gen);
            }
            gen.writeEndArray();
            gen.writeEndArray();
        }
    }

}
