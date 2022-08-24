package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectWithListMap {

    private static int counter;

    private final Map<MapKey, List<MapValue>> map;
    private final Map<MapKey, List<Integer>> map2;
    private final String someRandomString;
    private final Map<Integer, String> mapSimpleKey;
    private final Map<Integer, Integer> mapSimpleKey2;
    private final Map<Integer, MapValue> mapSimpleKey3;
    private final Map<Integer, List<MapValue>> mapSimpleKey4;

    @JsonCreator
    public ObjectWithListMap(@JsonProperty("map") Map<MapKey, List<MapValue>> map, @JsonProperty("someRandomString") String someRandomString) {
        this.map = map;
        this.someRandomString = someRandomString;
        mapSimpleKey = new HashMap<>();
        mapSimpleKey.put(1, "blip");
        mapSimpleKey2 = new HashMap<>();
        mapSimpleKey3 = new HashMap<>();
        mapSimpleKey4 = new HashMap<>();
        this.map2 = new HashMap<>();
        this.map.forEach((key, value) -> {
            this.map2.put(key, Arrays.asList(counter++, counter++));
            this.mapSimpleKey.put(counter++, String.valueOf(counter++));
            this.mapSimpleKey2.put(counter++, counter++);
            this.mapSimpleKey3.put(counter++, value.get(0));
            this.mapSimpleKey4.put(counter++, value);
        });
    }

    public Map<MapKey, List<MapValue>> getMap() {
        return map;
    }

    public String getSomeRandomString() {
        return someRandomString;
    }

    public Map<Integer, String> getMapSimpleKey() {
        return mapSimpleKey;
    }

    public Map<MapKey, List<Integer>> getMap2() {
        return map2;
    }

    public Map<Integer, Integer> getMapSimpleKey2() {
        return mapSimpleKey2;
    }

    public Map<Integer, MapValue> getMapSimpleKey3() {
        return mapSimpleKey3;
    }

    public Map<Integer, List<MapValue>> getMapSimpleKey4() {
        return mapSimpleKey4;
    }
}
