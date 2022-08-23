package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MapKeyImpl.class, name = "MapKey"),
})
public interface MapKey {
    String getName();
    long getNumber();
}
