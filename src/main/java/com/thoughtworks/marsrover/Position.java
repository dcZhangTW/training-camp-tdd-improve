package com.thoughtworks.marsrover;

import lombok.Getter;

@Getter
public class Position {
    private int x;
    private int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
