package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MapValueImpl.class, name = "MapValue"),
})
public interface MapValue {
    String getSurname();
    long getAge();
    List<FooBar> getFooBarList();
}
