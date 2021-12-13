package cern.lsa.mapping.example.domain;

import java.util.Map;
import java.util.TreeMap;

public class StandAloneBeamProcessImpl extends BeamProcessImpl implements StandAloneBeamProcess {

    private final Map<String, Attribute> attributes = new TreeMap<>();
    private String actualBeamProcessInfo;
    private boolean resident;
    private boolean isActual;

    public StandAloneBeamProcessImpl(long id, String name) {
        super(id, name);
    }

    @Override
    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }

    public Map<String, Attribute> getAttributes() {
        return attributes;
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

}