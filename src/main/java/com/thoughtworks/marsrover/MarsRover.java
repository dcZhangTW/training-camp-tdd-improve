package com.thoughtworks.marsrover;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class MarsRover {
    private MarsCar car;
    private int step = 1;
    private List<Position> pitList;
    private MarsMap map;

    MarsRover(int x, int y, Direction direction, MarsMap marsMap) {
        this.car = new MarsCar(x, y, direction);
        this.pitList = new ArrayList<>();
        this.map = marsMap;
    }

    void addPit(Position position) {
        this.pitList.add(position);
    }

    boolean checkInPit(Position position) {
        return this.pitList.stream().anyMatch(it -> it.equals(position));
    }

    Location execute(List<Instruction> instructions) {
        Location finallyLocation = car.getLocation();
        for (Instruction instruction : instructions) {
            Location newLocation = car.execute(instruction);
            Position carPosition = newLocation.getPosition();
            finallyLocation = newLocation;
            if (map.checkInPit(carPosition)) {
                pitList.add(carPosition);
                car = new MarsCar(car.getLocationHistories().get(car.getLocationHistories().size() - 1));
                break;
            }
        }
        return finallyLocation;
    }
}
