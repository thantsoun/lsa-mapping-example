package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FooBarImpl.class, name = "FooBar"),
})
public interface FooBar {
    String getFoo();
    String getBar();
}
