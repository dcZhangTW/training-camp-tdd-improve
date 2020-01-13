package com.thoughtworks.marsrover;

import lombok.Getter;

@Getter
class Location {
    private int x;
    private int y;
    private Direction direction;
    Location(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
