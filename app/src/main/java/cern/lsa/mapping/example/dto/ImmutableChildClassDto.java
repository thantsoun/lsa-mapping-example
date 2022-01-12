package cern.lsa.mapping.example.dto;

import cern.lsa.mapping.example.domain.DefaultImmutableChildClass;
import cern.lsa.mapping.example.domain.ImmutableSiblingClass;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(depluralize = true, typeImmutable = "Default*", get = { "get*", "is*", "are*" }, jdkOnly = true)
@JsonSerialize(as = DefaultImmutableChildClassDto.class)
@JsonDeserialize(as = DefaultImmutableChildClassDto.class)
public interface ImmutableChildClassDto extends ImmutableParentClassDto {
    String getSurname();
    ImmutableSiblingClass getSibling();
}
