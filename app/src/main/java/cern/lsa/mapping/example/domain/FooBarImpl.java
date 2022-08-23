package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FooBarImpl implements FooBar {

    private final String foo;
    private final String bar;

    @JsonCreator
    public FooBarImpl(@JsonProperty("foo") String foo, @JsonProperty("bar") String bar) {
        this.foo = foo;
        this.bar = bar;
    }

    @Override
    public String getFoo() {
        return foo;
    }

    @Override
    public String getBar() {
        return bar;
    }
}
