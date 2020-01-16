package com.thoughtworks.marsrover;

import lombok.Getter;

@Getter
class Location {
    private Position position;
    private Direction direction;
    Location(int x, int y, Direction direction) {
        this.position = new Position(x, y);
        this.direction = direction;
    }
}
