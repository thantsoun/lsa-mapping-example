package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.util.Map;

public class ObjectWithSimpleMap {

    @JsonSerialize(using = MyJsonSerializer.class)
//    @JsonDeserialize(using = MyJsonDeserializer.class)
    private final Map<MapKey, MapValue> map;
    private final String someRandomString;

    @JsonCreator
    public ObjectWithSimpleMap(@JsonProperty("map") Map<MapKey, MapValue> map, @JsonProperty("someRandomString") String someRandomString) {
        this.map = map;
        this.someRandomString = someRandomString;
    }

    public Map<MapKey, MapValue> getMap() {
        return map;
    }

    public String getSomeRandomString() {
        return someRandomString;
    }

    public static abstract class MyAbstractJsonSerializer<K, V> extends JsonSerializer<Map<K, V>> {

        @Override
        public void serialize(Map<K, V> value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeStartArray();
            for (Map.Entry<K, V> entry: value.entrySet()) {
                writeMapEntry(gen, provider, entry);
            }
            gen.writeEndArray();
        }

        private void writeMapEntry(JsonGenerator gen, SerializerProvider provider, Map.Entry<K, V> entry) throws IOException {
            gen.writeStartArray();
            provider.defaultSerializeValue(entry.getKey(), gen);
            provider.defaultSerializeValue(entry.getValue(), gen);
            gen.writeEndArray();
        }
    }

    public static class MyJsonSerializer extends MyAbstractJsonSerializer<MapKey, MapValue> { }
}
