package cern.lsa.mapping.example.dto.gsi;

import cern.lsa.mapping.example.dto.NameSurname;

public enum GsiNameSurname implements NameSurname {

    BOB_DYLAN("Bob", "Dylan", "Rock"),
    BOB_MARLEY("Bob", "Marley", "Reggae");

    private final String name;
    private final String surname;
    private final String style;

    GsiNameSurname(String name, String surname, String style) {
        this.name = name;
        this.surname = surname;
        this.style = style;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    public String getStyle() {
        return style;
    }

}
