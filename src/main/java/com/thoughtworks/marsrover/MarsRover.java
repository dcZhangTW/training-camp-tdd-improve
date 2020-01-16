package com.thoughtworks.marsrover;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class MarsRover {
    private Location location;
    private List<Location> locationHistories;
    private int step = 1;

    MarsRover(int x, int y, Direction direction) {
        this.location = new Location(x, y, direction);
        this.locationHistories = new ArrayList<>();
    }

    Location execute(List<Instruction> instructions) {
        instructions.forEach(it -> {
            switch (it) {
                case M:
                    executeM();
                    break;
                case L:
                    executeL();
                    break;
                case R:
                    executeR();
                    break;
                case B:
                    executeB();
                    break;
            }
        });
        return location;
    }

    private void executeB() {
        step = 0 - step;
    }

    private void executeR() {
        Direction direction = location.getDirection();
        Direction newDirection = Direction.of((direction.value + 1) % 4);
        locationHistories.add(location);
        location = new Location(location.getX(), location.getY(), newDirection);
    }

    private void executeL() {
        Direction direction = location.getDirection();
        Direction newDirection = Direction.of((direction.value + 3) % 4);
        locationHistories.add(location);
        location = new Location(location.getX(), location.getY(), newDirection);
    }

    private void executeM() {
        int y = location.getY();
        int x = location.getX();
        switch (location.getDirection()) {
            case N:
                y += step;
                break;
            case S:
                y -= step;
                break;
            case E:
                x += step;
                break;
            case W:
                x -= step;
                break;
        }
        locationHistories.add(location);
        location = new Location(x, y, location.getDirection());
    }
}
