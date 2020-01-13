package com.thoughtworks.marsrover;

import org.junit.Assert;
import org.junit.Test;

public class MarsRoverTest {
    @Test
    public void should_init_a_mars_rover_with_location_and_direction() {
        MarsRover marsRover = new MarsRover(0, 0, Direction.N);
        Location location = marsRover.getLocation();
        Assert.assertEquals(location.getX(), 0);
        Assert.assertEquals(location.getY(), 0);
        Assert.assertEquals(location.getDirection(), Direction.N);
    }
}
