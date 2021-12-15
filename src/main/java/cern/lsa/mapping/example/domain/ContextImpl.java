package cern.lsa.mapping.example.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class)
public abstract class ContextImpl implements Context {

    private Context parentContext;
    private List<Context> childrenContexts;
    private String typeName;
    private boolean multiplexed;
    private long id;
    private String name;
    private String accelerator;

    public ContextImpl(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Context> getChildrenContexts() {
        return childrenContexts;
    }

    public void setChildrenContexts(List<Context> childrenContexts) {
        this.childrenContexts = childrenContexts;
    }

    public String getAccelerator() {
        return this.accelerator;
    }

    public void setAccelerator(String accelerator) {
        this.accelerator = accelerator;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public Context getParent() {
        return this.parentContext;
    }

    public void setParent(Context parentContext) {
        this.parentContext = parentContext;
    }

    public void setMultiplexed(boolean multiplexed) {
        this.multiplexed = multiplexed;
    }

    public boolean isMultiplexed() {
        return this.multiplexed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}