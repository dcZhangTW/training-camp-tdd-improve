package com.thoughtworks.marsrover;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class MarsMap {
    private List<Position> pitList;

    MarsMap() {
        this.pitList = new ArrayList<>();
    }

    void addPit(Position position) {
        this.pitList.add(position);
    }

    boolean checkInPit(Position position) {
        return this.pitList.stream().anyMatch(it -> it.equals(position));
    }
}
