package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MapKeyImpl implements MapKey {

    private final String name;
    private final long number;

    @JsonCreator
    public MapKeyImpl(@JsonProperty("name") String name, @JsonProperty("number") long number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getNumber() {
        return number;
    }
}
