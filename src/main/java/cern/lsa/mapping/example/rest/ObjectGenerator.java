package cern.lsa.mapping.example.rest;

import cern.lsa.mapping.example.domain.*;

import java.util.Arrays;
import java.util.Collections;

public class ObjectGenerator {

    public static Attribute createAttribute(String name, long value) {
        Attribute attribute = new Attribute();
        attribute.setName(name);
        attribute.setValue(value);
        return attribute;
    }

    public static BeamProcessImpl createBMWithHierarchies(long id, String name) {
        BeamProcessImpl bm = createBM(id, name);
        BeamProcessImpl parent = createBM(id -1, "Parent of " + name);
        BeamProcessImpl child = createBM(id + 1, "Child of " + name);
        bm.setParent(parent);
        parent.setChildrenContexts(Collections.singletonList(bm));
        child.setParent(bm);
        bm.setChildrenContexts(Collections.singletonList(child));

        return bm;
    }

    private static BeamProcessImpl createBM(long id, String name) {
        BeamProcessImpl bm = new BeamProcessImpl(id, name);
        populateBM(bm);
        return bm;
    }

    public static StandAloneBeamProcessImpl createStandALoneBM(long id, String name) {
        StandAloneBeamProcessImpl bm = new StandAloneBeamProcessImpl(id, name);
        populateBM(bm);
        bm.setActual(true);
        bm.setActualBeamProcessInfo("Your info here");
        bm.setResident(true);
        bm.getAttributes().put("Attr", createAttribute("Attr", 123L));
        bm.setSomeImmutableClassList(Arrays.asList(
                ImmutableSomeImmutableClass.builder().attr1("1st immutable => Attr 1").attr2("1st immutable => Attr 2").build(),
                ImmutableSomeImmutableClass.builder().attr1("2nd immutable => Attr 1").attr2("2nd immutable => Attr 2").build()));
        return bm;
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
}
