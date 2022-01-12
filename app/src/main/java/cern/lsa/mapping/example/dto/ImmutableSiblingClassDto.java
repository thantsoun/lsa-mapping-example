package cern.lsa.mapping.example.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(depluralize = true, typeImmutable = "Default*", get = { "get*", "is*", "are*" }, jdkOnly = true)
@JsonSerialize(as = DefaultImmutableSiblingClassDto.class)
@JsonDeserialize(as = DefaultImmutableSiblingClassDto.class)
public interface ImmutableSiblingClassDto {
    String getSiblingName();
}
