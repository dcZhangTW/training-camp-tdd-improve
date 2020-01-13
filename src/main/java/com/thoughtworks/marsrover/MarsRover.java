package com.thoughtworks.marsrover;

import lombok.Getter;

import java.util.List;

@Getter
class MarsRover {
    private Location location;

    MarsRover(int x, int y, Direction direction) {
        this.location = new Location(x, y, direction);
    }

    Location execute(List<Instruction> instructions) {

        return location;
    }
}
