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
        if (Instruction.M.equals(firstSingleInstruction)) {
            return executeM();
        }
        return location;
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
