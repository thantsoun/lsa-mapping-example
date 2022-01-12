package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(depluralize = true, typeImmutable = "Default*", get = { "get*", "is*", "are*" }, jdkOnly = true)
@JsonSerialize(as = DefaultImmutableSiblingClass.class)
@JsonDeserialize(as = DefaultImmutableSiblingClass.class)
public interface ImmutableSiblingClass {
    String getSiblingName();
}
