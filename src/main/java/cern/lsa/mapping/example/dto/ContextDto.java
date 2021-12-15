package cern.lsa.mapping.example.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BeamProcessDto.class, name = "BeamProcessDto"),
        @JsonSubTypes.Type(value = StandAloneBeamProcessDto.class, name = "StandAloneBeamProcessDto"),
})
public abstract class ContextDto {

    private ContextDto parentContext;
    private List<ContextDto> childrenContexts;
    private String typeName;
    private boolean multiplexed;
    private long id;
    private String name;
    private String accelerator;

    protected ContextDto() {
    }

    public ContextDto getParentContext() {
        return parentContext;
    }

    public void setParentContext(ContextDto parentContext) {
        this.parentContext = parentContext;
    }

    public void setChildrenContexts(List<ContextDto> childrenContexts) {
        this.childrenContexts = childrenContexts;
    }

    public List<ContextDto> getChildrenContexts() {
        return childrenContexts;
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