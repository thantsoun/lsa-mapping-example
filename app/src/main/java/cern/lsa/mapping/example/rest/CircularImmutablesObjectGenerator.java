package cern.lsa.mapping.example.rest;

import cern.lsa.mapping.example.referenced.ModifiableReferencedCircularImmutable;
import cern.lsa.mapping.example.referenced.ReferencedCircularImmutable;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class CircularImmutablesObjectGenerator {

    private final IdentityHashMap<ReferencedCircularImmutable, AtomicReference<ReferencedCircularImmutable>> referenceMap = new IdentityHashMap<>();

    public ModifiableReferencedCircularImmutable createModifiableCircular(String title, String name, String message) {
        ModifiableReferencedCircularImmutable obj = ModifiableReferencedCircularImmutable.create()
                .setName(name)
                .setMessage(message)
                .setTitle(title)
                .setParentInt(null)
                .setChildrenInt(Collections.emptyList())
                .setUniqueId(UUID.randomUUID().toString());
        referenceMap.put(obj, new AtomicReference<>(obj));
        return obj;
    }

    public void setChildren(ModifiableReferencedCircularImmutable node, List<ModifiableReferencedCircularImmutable> children) {
        children.forEach(child -> {
            if (!referenceMap.containsKey(child)) {
                throw new IllegalArgumentException(child + " has not been created by this generator");
            }
        });
        node.setChildrenInt(children.stream().map(referenceMap::get).collect(Collectors.toList()));
    }

    public void setParent(ModifiableReferencedCircularImmutable node, ModifiableReferencedCircularImmutable parent) {
        if (!referenceMap.containsKey(parent)) {
            throw new IllegalArgumentException(parent + " has not been created by this generator");
        }
        node.setParentInt(referenceMap.get(parent));
    }
}
