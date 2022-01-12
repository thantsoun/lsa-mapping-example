package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(depluralize = true, typeImmutable = "Default*", get = { "get*", "is*", "are*" }, jdkOnly = true)
@JsonSerialize(as = DefaultImmutableParentClass.class)
@JsonDeserialize(as = DefaultImmutableParentClass.class)
public interface ImmutableParentClass {
    String getName();
    List<String> getAliases();
}
