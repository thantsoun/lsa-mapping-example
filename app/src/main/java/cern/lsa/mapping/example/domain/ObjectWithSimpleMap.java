package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class ObjectWithSimpleMap {


    private static int counter;

    private final Map<MapKey, MapValue[]> map;
    private final Map<MapKey, MapValue> map2;
    private final Map<MapKey, Integer> map3;
    private final Map<MapKey, String> map4;
    private final String someRandomString;

    @JsonCreator
    public ObjectWithSimpleMap(@JsonProperty("map") Map<MapKey, MapValue[]> map, @JsonProperty("someRandomString") String someRandomString) {
        this.map = map;
        this.map2 = new HashMap<>();
        this.map3 = new HashMap<>();
        this.map4 = new HashMap<>();
        this.map.forEach((key, value) -> {
            this.map2.put(key, value[0]);
            this.map3.put(key, counter++);
            this.map4.put(key, String.valueOf(counter++));
        });
        this.someRandomString = someRandomString;
    }

    public Map<MapKey, MapValue[]> getMap() {
        return map;
    }

    public Map<MapKey, MapValue> getMap2() {
        return map2;
    }

    public Map<MapKey, Integer> getMap3() {
        return map3;
    }

    public Map<MapKey, String> getMap4() {
        return map4;
    }

    public String getSomeRandomString() {
        return someRandomString;
    }

}
