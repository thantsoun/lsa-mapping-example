package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MapValueImpl implements MapValue {

    private final String surname;
    private final long age;

    @JsonCreator
    public MapValueImpl(@JsonProperty("surname") String surname, @JsonProperty("age") long age) {
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public long getAge() {
        return age;
    }
}
