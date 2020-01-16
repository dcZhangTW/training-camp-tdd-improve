package com.thoughtworks.marsrover;

import lombok.Getter;

@Getter
class Position {
    private int x;
    private int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean equals(Position position) {
        return x == position.getX() && y == position.getY();
    }
}
