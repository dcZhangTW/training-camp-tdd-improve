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
        Instruction firstSingleInstruction = instructions.get(0);
        switch (firstSingleInstruction) {
            case M:
                return executeM();
            case L:
                return executeL();
        }
        return location;
    }

    private Location executeL() {
        Direction direction = location.getDirection();
        switch (location.getDirection()) {
            case N:
                direction = Direction.W;
                break;
            case S:
                direction = Direction.E;
                break;
            case E:
                direction = Direction.N;
                break;
            case W:
                direction = Direction.S;
                break;
        }
        return new Location(location.getX(), location.getY(), direction);
    }

    private Location executeM() {
        int y = location.getY();
        int x = location.getX();
        switch (location.getDirection()) {
            case N:
                y += 1;
                break;
            case S:
                y -= 1;
                break;
            case E:
                x += 1;
                break;
            case W:
                x -= 1;
                break;
        }
        return new Location(x, y, location.getDirection());
    }
}
