package com.thoughtworks.marsrover;

import lombok.Getter;

import java.util.List;

@Getter
public class MarsRover {
    private Location location;

    public MarsRover(int x, int y, Direction direction) {
        this.location = new Location(x, y, direction);
    }

    public Location execute(List<Instruction> instructions) {
        return location;
    }
}
