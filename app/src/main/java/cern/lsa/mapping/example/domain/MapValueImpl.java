package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MapValueImpl implements MapValue {

    private final String surname;
    private final long age;
    private final List<FooBar> fooBarList;

    @JsonCreator
    public MapValueImpl(@JsonProperty("surname") String surname, @JsonProperty("age") long age, @JsonProperty("fooBarList") List<FooBar> fooBarList) {
        this.surname = surname;
        this.age = age;
        this.fooBarList = fooBarList;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public long getAge() {
        return age;
    }

    @Override
    public List<FooBar> getFooBarList() {
        return fooBarList;
    }
}
