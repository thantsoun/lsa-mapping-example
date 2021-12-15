package cern.lsa.mapping.example.dto.gsi;

import cern.lsa.mapping.example.dto.BeamProcessDto;

public class GsiBeamProcessDto extends BeamProcessDto {

    private String gsiString;

    public String getGsiString() {
        return gsiString;
    }

    public void setGsiString(String gsiString) {
        this.gsiString = gsiString;
    }
}