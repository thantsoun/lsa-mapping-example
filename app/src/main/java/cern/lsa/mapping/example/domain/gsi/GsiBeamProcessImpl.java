package cern.lsa.mapping.example.domain.gsi;

import cern.lsa.mapping.example.domain.BeamProcessImpl;

public class GsiBeamProcessImpl extends BeamProcessImpl {

    private String gsiString;

    public GsiBeamProcessImpl(long id, String name) {
        super(id, name);
    }

    public String getGsiString() {
        return gsiString;
    }

    public void setGsiString(String gsiString) {
        this.gsiString = gsiString;
    }
}