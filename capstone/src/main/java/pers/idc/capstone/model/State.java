package pers.idc.capstone.model;

import com.google.common.collect.Sets;

import java.util.Set;

import static pers.idc.capstone.model.Area.*;

public enum State {
    STATE_A(Sets.newHashSet(AREA_1, AREA_2)),
    STATE_B(Sets.newHashSet(AREA_3)),
    STATE_C(Sets.newHashSet(AREA_4, AREA_5));

    private final Set<Area> areas;

    State(Set<Area> areas) {
        this.areas = areas;
    }

    public Set<Area> getAreas() {
        return this.areas;
    }
}
