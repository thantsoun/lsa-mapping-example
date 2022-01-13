package cern.lsa.mapping.example.immutablestest;

import org.immutables.value.Value;

@Value.Immutable
public interface SomeImmutableClassDto {
    String getName();
}
