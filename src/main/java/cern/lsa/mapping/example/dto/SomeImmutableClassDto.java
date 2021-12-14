package cern.lsa.mapping.example.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(depluralize = true, typeImmutable = "Default*", get = { "get*", "is*", "are*" }, jdkOnly = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DefaultSomeImmutableClassDto.class, name = "SomeImmutableClassDto")
})
public interface SomeImmutableClassDto {
    String getAttr1();
    String getAttr2();
}
