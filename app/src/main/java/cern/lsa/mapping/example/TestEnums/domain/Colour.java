package cern.lsa.mapping.example.TestEnums.domain;

// this will work with mix-ins
public enum Colour implements Named {

    RED("#ff0000"), GREEN("#00FF00");

    private final String hex;

    Colour(String hex) {
        this.hex = hex;
    }

    @Override
    public String getName() {
        return "Colour " + name();
    }

    public String getHex() {
        return hex;
    }

    @Override
    public String toString() {
        return "Colour{" +
                "hex='" + hex + '\'' +
                '}';
    }
}
