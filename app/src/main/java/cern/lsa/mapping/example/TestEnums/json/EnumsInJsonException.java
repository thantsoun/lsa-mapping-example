package cern.lsa.mapping.example.TestEnums.json;

public class EnumsInJsonException extends IllegalArgumentException {

    public EnumsInJsonException(String msg) {
        super(msg);
    }

    public EnumsInJsonException(String msg, Exception e) {
        super(msg, e);
    }
}
