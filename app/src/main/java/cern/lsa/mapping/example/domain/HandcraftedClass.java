package cern.lsa.mapping.example.domain;

public class HandcraftedClass {

    private ImmutableParentClass parent;
    private ImmutableChildClass child;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImmutableParentClass getParent() {
        return parent;
    }

    public void setParent(ImmutableParentClass parent) {
        this.parent = parent;
    }

    public ImmutableChildClass getChild() {
        return child;
    }

    public void setChild(ImmutableChildClass child) {
        this.child = child;
    }
}
