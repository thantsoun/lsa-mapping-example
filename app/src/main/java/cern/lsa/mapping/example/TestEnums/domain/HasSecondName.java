package cern.lsa.mapping.example.TestEnums.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MyEnum.class, name = "my_enum"),
        @JsonSubTypes.Type(value = DuplicateEnum.class, name = "duplicate_enum"),
        @JsonSubTypes.Type(value = Person.class, name = "person"),
})
public interface HasSecondName extends Named {
    String getSecondName();
}
