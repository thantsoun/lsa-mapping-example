package cern.lsa.mapping.example.TestEnums.domain;

import cern.lsa.mapping.example.TestEnums.json.EnumAsJsonObject;

@EnumAsJsonObject
public enum DuplicateEnum implements HasSecondName {

    BLA("BLA"), BLA_BLA("BLA");

    private final String name;

    DuplicateEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DuplicateEnum{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public String getSecondName() {
        return "Second " + name();
    }
}
