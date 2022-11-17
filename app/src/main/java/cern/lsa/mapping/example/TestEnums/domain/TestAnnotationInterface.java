package cern.lsa.mapping.example.TestEnums.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TestAnnotationInterface {

    public interface Ifce {
        @JsonIgnore
        String getPassword();
        String getName();
    }

    public static class Class implements Ifce {

        @Override
        public String getPassword() {
            return "password";
        }

        @Override
        public String getName() {
            return "Thanos";
        }
    }
}
