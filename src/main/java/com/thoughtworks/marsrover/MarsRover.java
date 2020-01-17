package com.thoughtworks.marsrover;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class MarsRover {
    private MarsCar car;
    private MarsMap map;
    private int step = 1;
    private List<Position> pitList = new ArrayList<>();;
    private List<MarsCar> scrappedCars = new ArrayList<>();

    MarsRover(int x, int y, Direction direction, MarsMap marsMap) {
        this.car = new MarsCar(x, y, direction);
        this.map = marsMap;
    }

    void addPit(Position position) {
        pitList.add(position);
    }

    boolean checkInPit(Position position) {
        return pitList.stream().anyMatch(it -> it.equals(position));
    }

    Location execute(List<Instruction> instructions) {
        Location finallyLocation = car.getLocation();
        for (Instruction instruction : instructions) {
            Location newLocation = car.preCalculateLocation(instruction);
            Position carPosition = newLocation.getPosition();

            if (checkInPit(carPosition)) {
                continue;
            }

            car.doInstruction(newLocation);
            finallyLocation = newLocation;

            if (map.checkInPit(carPosition)) {
                pitList.add(carPosition);
                scrappedCars.add(car);
                car = new MarsCar(car.getLocationHistories().get(car.getLocationHistories().size() - 1));
                break;
            }
        }
        return finallyLocation;
    }
}
