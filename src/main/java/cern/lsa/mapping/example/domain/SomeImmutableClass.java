package cern.lsa.mapping.example.domain;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(depluralize = true, typeImmutable = "Default*", get = { "get*", "is*", "are*" }, jdkOnly = true)
public interface SomeImmutableClass {
    String getAttr1();
    String getAttr2();
}
