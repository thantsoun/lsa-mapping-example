package cern.lsa.mapping.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NamesContext {

    private final NameSurname first;
    private final NameSurname second;
    private final NameSurname third;

    @JsonCreator
    public NamesContext(@JsonProperty("first") NameSurname first, @JsonProperty("second") NameSurname second, @JsonProperty("third") NameSurname third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public NameSurname getFirst() {
        return first;
    }

    public NameSurname getSecond() {
        return second;
    }

    public NameSurname getThird() {
        return third;
    }
}