package com.thoughtworks.marsrover;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MarsRoverTest {
    @Test
    public void should_init_a_mars_rover_with_location_and_direction() {
        MarsRover marsRover = new MarsRover(0, 0, Direction.N);
        Location location = marsRover.getLocation();
        Assert.assertEquals(location.getX(), 0);
        Assert.assertEquals(location.getY(), 0);
        Assert.assertEquals(location.getDirection(), Direction.N);
    }

    @Test
    public void should_Move_1_when_face_N_and_get_single_M() {
        MarsRover marsRover = new MarsRover(0,0, Direction.N);
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.M);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.N);
        Assert.assertEquals(location.getX(), 0);
        Assert.assertEquals(location.getY(), 1);
    }

    @Test
    public void should_Move_1_when_face_S_and_get_single_M() {
        MarsRover marsRover = new MarsRover(0,0, Direction.S);
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.M);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.S);
        Assert.assertEquals(location.getX(), 0);
        Assert.assertEquals(location.getY(), -1);
    }

    @Test
    public void should_Rotate_L_90_single_instruction() {
        MarsRover marsRover = new MarsRover(0,0, Direction.N);
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.L);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.W);
        Assert.assertEquals(location.getX(), 0);
        Assert.assertEquals(location.getY(), 0);
    }
}
