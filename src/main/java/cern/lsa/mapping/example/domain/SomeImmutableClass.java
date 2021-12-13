package cern.lsa.mapping.example.domain;

import org.immutables.value.Value;

@Value.Immutable
//@Value.Style(init = "set*")
public interface SomeImmutableClass {
    String attr1();
    String attr2();
}
