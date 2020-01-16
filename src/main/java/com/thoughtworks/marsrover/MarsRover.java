package com.thoughtworks.marsrover;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class MarsRover {
    private Location location;
    private List<Location> locationHistories;
    private int step = 1;
    private List<Position> pitList;

    MarsRover(int x, int y, Direction direction) {
        this.location = new Location(x, y, direction);
        this.locationHistories = new ArrayList<>();
        this.pitList = new ArrayList<>();
    }

    void addPit(Position position) {
        this.pitList.add(position);
    }

    boolean checkInPit(Position position) {
        return this.pitList.stream().anyMatch(it -> it.equals(position));
    }

    Location execute(List<Instruction> instructions) {
        instructions.forEach(this::executeInstruction);
        return location;
    }

    private void executeInstruction(Instruction instruction) {
        Location newLocation = location;
        switch (instruction) {
            case M:
                newLocation = executeM();
                break;
            case L:
                newLocation = executeL();
                break;
            case R:
                newLocation = executeR();
                break;
            case B:
                executeB();
                break;
        }
        locationHistories.add(location);
        location = newLocation;
    }

    private void executeB() {
        step = 0 - step;
    }

    private Location executeR() {
        Direction direction = location.getDirection();
        Direction newDirection = Direction.of((direction.value + ((4 + step) % 4)) % 4);
        locationHistories.add(location);
        return new Location(location.getX(), location.getY(), newDirection);
    }

    private Location executeL() {
        Direction direction = location.getDirection();
        Direction newDirection = Direction.of((direction.value + ((4 - step) % 4)) % 4);
        locationHistories.add(location);
        return new Location(location.getX(), location.getY(), newDirection);
    }

    private Location executeM() {
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
        return new Location(x, y, location.getDirection());
    }
}
