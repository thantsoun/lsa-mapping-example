package cern.lsa.mapping.example.TestEnums.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person implements HasSecondName {

    private final String name;
    private final String secondName;

    @JsonCreator
    public Person(@JsonProperty("name") String name, @JsonProperty("secondName") String secondName) {
        this.name = name;
        this.secondName = secondName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSecondName() {
        return secondName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
