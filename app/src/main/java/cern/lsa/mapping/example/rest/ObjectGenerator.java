package cern.lsa.mapping.example.rest;

import cern.lsa.mapping.example.domain.*;
import cern.lsa.mapping.example.domain.gsi.GsiBeamProcessImpl;

import java.util.*;

public class ObjectGenerator {

    public static ObjectWithListMap createObjectWithListMap() {
        return new ObjectWithListMap(getKeyListMap(), "Hello");
    }

    public static  Map<MapKey, List<MapValue>> createRawListMap() {
        return getKeyListMap();
    }

    public static Map<MapKey, List<MapValue>> getKeyListMap() {
        Map<MapKey, List<MapValue>> map = new HashMap<>();
        map.put(new MapKeyImpl("Thanos", 1),
                Arrays.asList(
                        new MapValueImpl("Tsounis", 44, Arrays.asList(new FooBarImpl("foo1", "bar1"), new FooBarImpl("foo2", "bar2"))),
                        new MapValueImpl("Fadakis", 30, Arrays.asList(new FooBarImpl("foo3", "bar3"), new FooBarImpl("foo4", "bar4")))
                )
        );
        map.put(new MapKeyImpl("Roman", 2),
                Arrays.asList(
                        new MapValueImpl("Gorbonosov", 42, Arrays.asList(new FooBarImpl("foo5", "bar5"), new FooBarImpl("foo6", "bar6"))),
                        new MapValueImpl("Abramovic", 99, Arrays.asList(new FooBarImpl("foo7", "bar7"), new FooBarImpl("foo8", "bar8")))
                )
        );
        return map;
    }

    public static ObjectWithSimpleMap createObjectWithSimpleMap() {
        Map<MapKey, Object[]> map = new HashMap<>();
        map.put(new MapKeyImpl("Thanos", 1),
                        new MapValue[] { new MapValueImpl("Tsounis", 44, Arrays.asList(new FooBarImpl("foo1", "bar1"), new FooBarImpl("foo2", "bar2"))) }

        );
        map.put(new MapKeyImpl("Roman", 2),
                new Object[] { new MapValueImpl("Gorbonosov", 42, Arrays.asList(new FooBarImpl("foo5", "bar5"), new FooBarImpl("foo6", "bar6"))) }
        );
        return new ObjectWithSimpleMap(map, "Hello");
    }

    public static Attribute createAttribute(String name, long value) {
        Attribute attribute = new Attribute();
        attribute.setName(name);
        attribute.setValue(value);
        return attribute;
    }

    public static BeamProcessImpl createBMWithHierarchies(long id, String name) {
        BeamProcessImpl beamProcess = createBeamProcess(id, name);
        BeamProcessImpl parent = createBeamProcess(id -1, "Parent of " + name);
        BeamProcessImpl child = createBeamProcess(id + 1, "Child of " + name);
        beamProcess.setParent(parent);
        parent.setChildrenContexts(Collections.singletonList(beamProcess));
        child.setParent(beamProcess);
        beamProcess.setChildrenContexts(Collections.singletonList(child));

        return beamProcess;
    }

    private static BeamProcessImpl createBeamProcess(long id, String name) {
        BeamProcessImpl beamProcess = new BeamProcessImpl(id, name);
        populateBM(beamProcess);
        return beamProcess;
    }

    private static StandAloneBeamProcessImpl createStandAloneBeamProcess(long id, String name) {
        StandAloneBeamProcessImpl standAloneBeamProcess = new StandAloneBeamProcessImpl(id, name);
        populateBM(standAloneBeamProcess);
        return standAloneBeamProcess;
    }

    private static GsiBeamProcessImpl createGisBeamProcess(long id, String name) {
        GsiBeamProcessImpl gsiBeamProcess = new GsiBeamProcessImpl(id, name);
        populateBM(gsiBeamProcess);
        gsiBeamProcess.setGsiString("I am a GSI class");
        return gsiBeamProcess;
    }

    public static StandAloneBeamProcessImpl createStandALoneBM(long id, String name) {
        StandAloneBeamProcessImpl standAloneBeamProcess = createStandAloneBeamProcess(id, name);
        addStandALoneExtraFields(standAloneBeamProcess);

        GsiBeamProcessImpl parent = createGisBeamProcess(id - 1, "Parent of " + name);
        parent.setChildrenContexts(Collections.singletonList(standAloneBeamProcess));
        standAloneBeamProcess.setParent(parent);

        BeamProcessImpl child = createBeamProcess(id + 1, "Child of " + name);
        child.setParent(standAloneBeamProcess);
        standAloneBeamProcess.setChildrenContexts(Collections.singletonList(child));

        return standAloneBeamProcess;
    }

    private static void addStandALoneExtraFields(StandAloneBeamProcessImpl standAloneBeamProcess) {
        standAloneBeamProcess.setActual(true);
        standAloneBeamProcess.setActualBeamProcessInfo("Your info here");
        standAloneBeamProcess.setResident(true);
        standAloneBeamProcess.getAttributes().put("Attr", createAttribute("Attr", 123L));
        standAloneBeamProcess.setSomeImmutableClassList(Arrays.asList(
                DefaultSomeImmutableClass.builder().attr1("1st immutable => Attr 1").attr2("1st immutable => Attr 2").build(),
                DefaultSomeImmutableClass.builder().attr1("2nd immutable => Attr 1").attr2("2nd immutable => Attr 2").build()));
    }

    private static void populateBM(BeamProcessImpl bm) {
        bm.setPurpose("I have a purpose in life");
        bm.setParticleTransfer("Here we transfer particles");
        bm.setAccelerator("LHC");
        bm.setMultiplexed(true);
        bm.setStartTime(100);
        bm.setUser("Best user in town");
        bm.setTypeName("I have such a cool type");
        bm.setCategory("My category in not categorized");
    }

    public static CircularImmutablesObjectGenerator getCircularObjGenerator() {
        return new CircularImmutablesObjectGenerator();
    }

    public static ImmutableChildClass createImmutableChildClass(String name, String surname, Collection<String> aliases, ImmutableSiblingClass immutableSiblingClass) {
        return DefaultImmutableChildClass.builder()
                .name(name)
                .surname(surname)
                .sibling(immutableSiblingClass)
                .aliases(aliases)
                .build();
    }

    public static ImmutableParentClass createImmutableParentClass(String name, Collection<String> aliases) {
        return DefaultImmutableParentClass.builder()
                .name(name)
                .aliases(aliases)
                .build();
    }

    public static ImmutableSiblingClass createImmutableSiblingClass(String name) {
        return DefaultImmutableSiblingClass.builder()
                .siblingName(name)
                .build();
    }

    public static HandcraftedClass createHandCraftedClass(ImmutableParentClass immutableParentClass, ImmutableChildClass immutableChildClass) {
        HandcraftedClass handcraftedClass = new HandcraftedClass();
        handcraftedClass.setChild(immutableChildClass);
        handcraftedClass.setParent(immutableParentClass);
        return handcraftedClass;
    }

}
