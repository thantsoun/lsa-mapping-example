package cern.lsa.mapping.example.dto;

import cern.lsa.mapping.example.domain.DefaultImmutableSiblingClass;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(depluralize = true, typeImmutable = "Default*", get = { "get*", "is*", "are*" }, jdkOnly = true)
@JsonSerialize(as = DefaultImmutableParentClassDto.class)
@JsonDeserialize(as = DefaultImmutableParentClassDto.class)
public interface ImmutableParentClassDto {
    String getName();
    List<String> getAliases();
}
