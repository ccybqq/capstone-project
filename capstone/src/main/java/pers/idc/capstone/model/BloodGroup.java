package pers.idc.capstone.model;

public enum BloodGroup {
    AB_P("AB+"),
    AB_N("AB-"),
    A_P("A+"),
    A_N("A-"),
    B_P("B+"),
    B_N("B-"),
    O_P("O+"),
    O_N("O-");

    private final String name;

    BloodGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
