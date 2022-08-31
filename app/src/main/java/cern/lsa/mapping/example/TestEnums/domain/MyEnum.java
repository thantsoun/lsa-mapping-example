package cern.lsa.mapping.example.TestEnums.domain;

import cern.lsa.mapping.example.TestEnums.json.EnumAsJsonObject;

@EnumAsJsonObject
public enum MyEnum implements HasSecondName {

    BLA("B L A"), BLA_BLA("BLA-BLA");

    private final String name;

    MyEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getSecondName() {
        return "Second " + name;
    }

    @Override
    public String toString() {
        return "MyEnum{" +
                "name='" + name + '\'' +
                '}';
    }
}
