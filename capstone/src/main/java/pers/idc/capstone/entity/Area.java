package pers.idc.capstone.entity;

public enum Area {
    AREA_1("Area 1"),
    AREA_2("Area 2"),
    AREA_3("Area 3"),
    AREA_4("Area 4"),
    AREA_5("Area 5");

    private final String name;

    Area(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
