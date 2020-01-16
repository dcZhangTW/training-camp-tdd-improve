package com.thoughtworks.marsrover;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class MarsCar {

    private Location location;
    private List<Location> locationHistories;
    private int step = 1;

    MarsCar(int x, int y, Direction direction) {
        this.location = new Location(x, y, direction);
        this.locationHistories = new ArrayList<>();
        this.locationHistories.add(this.location);
    }

    MarsCar(Location location) {
        this.location = location;
        this.locationHistories = new ArrayList<>();
        this.locationHistories.add(this.location);
    }

    Location execute(Instruction instruction) {
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
        locationHistories.add(newLocation);
        location = newLocation;
        return location;
    }

    private void executeB() {
        step = 0 - step;
    }

    private Location executeR() {
        Direction direction = location.getDirection();
        Direction newDirection = Direction.of((direction.value + ((4 + step) % 4)) % 4);
        return new Location(location.getPosition().getX(), location.getPosition().getY(), newDirection);
    }

    private Location executeL() {
        Direction direction = location.getDirection();
        Direction newDirection = Direction.of((direction.value + ((4 - step) % 4)) % 4);
        return new Location(location.getPosition().getX(), location.getPosition().getY(), newDirection);
    }

    private Location executeM() {
        int y = location.getPosition().getY();
        int x = location.getPosition().getX();
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
        return new Location(x, y, location.getDirection());
    }
}
