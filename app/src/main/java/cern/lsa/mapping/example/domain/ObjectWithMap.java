package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectWithMap {

//    @JsonSerialize(converter = MyMapConverter.class)
//    @JsonDeserialize(converter = MyMapBackConverter.class)
    @JsonSerialize(using = MyJsonSerializer.class)
//    @JsonDeserialize(using = MyJsonDeserializer.class)
    private final Map<MapKey, List<MapValue>> map;
    private final String someRandomString;

    @JsonCreator
    public ObjectWithMap(@JsonProperty("map") Map<MapKey, List<MapValue>> map, @JsonProperty("someRandomString") String someRandomString) {
        this.map = map;
        this.someRandomString = someRandomString;
    }

    public Map<MapKey, List<MapValue>> getMap() {
        return map;
    }

    public String getSomeRandomString() {
        return someRandomString;
    }

    public static class KeyValuePair<K, V> {

        private K key;
        private V value;

        @JsonCreator
        public KeyValuePair(@JsonProperty("key") K key, @JsonProperty("value") V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static abstract class AbstractMapToPairs<K, V> extends StdConverter<Map<K, V>, List<KeyValuePair<K, V>>> {
        @Override
        public List<KeyValuePair<K, V>> convert(Map<K, V> value) {
            return value.entrySet().stream().map(entry -> new KeyValuePair<>(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        }
    }

    public static abstract class AbstractPairsToMap<K, V> extends StdConverter<List<KeyValuePair<K, V>>, Map<K, V>> {
        @Override
        public Map<K, V> convert(List<KeyValuePair<K, V>> values) {
            return values.stream().collect(Collectors.toMap(KeyValuePair::getKey, KeyValuePair::getValue));
        }
    }

    public static class MyMapConverter extends AbstractMapToPairs<MapKey, List<MapValue>> {};

    public static class MyMapBackConverter extends AbstractPairsToMap<MapKey, List<MapValue>> {};

    public static abstract class MyAbstractJsonSerializer<K, V> extends JsonSerializer<Map<K, List<V>>> {

        @Override
        public void serialize(Map<K, List<V>> value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeStartArray();
            for (Map.Entry<K, List<V>> entry: value.entrySet()) {
                writeMapEntry(gen, provider, entry);
            }
            gen.writeEndArray();
        }

        private void writeMapEntry(JsonGenerator gen, SerializerProvider provider, Map.Entry<K, List<V>> entry) throws IOException {
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

    public static class MyJsonSerializer extends MyAbstractJsonSerializer<MapKey, MapValue> { }
}
