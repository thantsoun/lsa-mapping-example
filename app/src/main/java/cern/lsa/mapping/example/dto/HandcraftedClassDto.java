package cern.lsa.mapping.example.dto;

public class HandcraftedClassDto {

    private String name;
    private ImmutableParentClassDto parent;
    private ImmutableChildClassDto child;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImmutableParentClassDto getParent() {
        return parent;
    }

    public void setParent(ImmutableParentClassDto parent) {
        this.parent = parent;
    }

    public ImmutableChildClassDto getChild() {
        return child;
    }

    public void setChild(ImmutableChildClassDto child) {
        this.child = child;
    }
}
