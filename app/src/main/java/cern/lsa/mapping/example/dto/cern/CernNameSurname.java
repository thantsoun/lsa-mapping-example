package cern.lsa.mapping.example.dto.cern;

import cern.lsa.mapping.example.dto.CommonNameSurname;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CernNameSurname extends CommonNameSurname {

    private final String supervisor;

    @JsonCreator
    public CernNameSurname(@JsonProperty("name") String name, @JsonProperty("surname") String surname, @JsonProperty("supervisor") String supervisor) {
       super(name, surname);
        this.supervisor = supervisor;
    }


    public String getSupervisor() {
        return supervisor;
    }
}
