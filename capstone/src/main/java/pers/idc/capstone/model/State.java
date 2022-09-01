package pers.idc.capstone.model;

import com.google.common.collect.Sets;

import java.util.Set;

import static pers.idc.capstone.model.Area.*;

public enum State {
    STATE_A("State A", Sets.newHashSet(AREA_1, AREA_2)),
    STATE_B("State B", Sets.newHashSet(AREA_3)),
    STATE_C("State C", Sets.newHashSet(AREA_4, AREA_5));

    private final String name;
    private final Set<Area> areas;

    State(String name, Set<Area> areas) {
        this.name = name;
        this.areas = areas;
    }

    public Set<Area> getAreas() {
        return this.areas;
    }
}
