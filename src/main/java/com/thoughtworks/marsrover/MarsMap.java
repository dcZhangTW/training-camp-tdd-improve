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

    boolean checkInPit(Position position) {
        double checkDbl = (double) (position.getX() + position.getY());
        return Math.abs(Math.sin(checkDbl)) > 0.5;
    }
}
