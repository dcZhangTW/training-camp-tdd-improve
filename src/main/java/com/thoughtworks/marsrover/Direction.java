package com.thoughtworks.marsrover;

import java.util.Arrays;

public enum Direction {
    N(0),
    E(1),
    S(2),
    W(3);

    public final int value;

    Direction(int value) {
        this.value = value;
    }

    public static Direction of(int value) {
        return Arrays.stream(Direction.values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElse(null);
    }
}
