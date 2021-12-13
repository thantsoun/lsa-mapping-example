package cern.lsa.mapping.example.dto;

import cern.lsa.mapping.example.domain.StandAloneBeamProcess;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StandAloneBeamProcessDto extends BeamProcessDto implements StandAloneBeamProcess {

    private Map<String, AttributeDto> attributes = new TreeMap<>();
    private String actualBeamProcessInfo;
    private boolean resident;
    private boolean isActual;
    private List<SomeImmutableClassDto> someImmutableClassDtoList;

    @Override
    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }

    public Map<String, AttributeDto> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, AttributeDto> attributes) {
        this.attributes = attributes;
    }

    public String getActualBeamProcessInfo() {
        return actualBeamProcessInfo;
    }

    public void setActualBeamProcessInfo(String actualBeamProcessInfo) {
        this.actualBeamProcessInfo = actualBeamProcessInfo;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }

    public List<SomeImmutableClassDto> getSomeImmutableClassDtoList() {
        return someImmutableClassDtoList;
    }

    public void setSomeImmutableClassDtoList(List<SomeImmutableClassDto> someImmutableClassDtoList) {
        this.someImmutableClassDtoList = someImmutableClassDtoList;
    }
}