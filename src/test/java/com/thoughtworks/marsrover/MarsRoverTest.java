package com.thoughtworks.marsrover;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MarsRoverTest {

    private MarsMap marsMap;

    @Before
    public void setUp() {
        marsMap = mock(MarsMap.class);
    }

    @Test
    public void should_init_a_mars_rover_with_location_and_direction() {
        MarsRover marsRover = new MarsRover(0, 0, Direction.N, marsMap);
        Location location = marsRover.getCar().getLocation();
        Assert.assertEquals(location.getPosition().getX(), 0);
        Assert.assertEquals(location.getPosition().getY(), 0);
        Assert.assertEquals(location.getDirection(), Direction.N);
    }

    @Test
    public void should_Move_1_when_face_N_and_get_single_M() {
        MarsRover marsRover = new MarsRover(0,0, Direction.N, marsMap);

        when(marsMap.checkInPit(any(Position.class))).thenReturn(false);

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.M);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.N);
        Assert.assertEquals(location.getPosition().getX(), 0);
        Assert.assertEquals(location.getPosition().getY(), 1);
    }

    @Test
    public void should_Move_1_when_face_S_and_get_single_M() {
        MarsRover marsRover = new MarsRover(0,0, Direction.S, marsMap);

        when(marsMap.checkInPit(any(Position.class))).thenReturn(false);

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.M);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.S);
        Assert.assertEquals(location.getPosition().getX(), 0);
        Assert.assertEquals(location.getPosition().getY(), -1);
    }

    @Test
    public void should_Rotate_L_90_single_instruction() {
        MarsRover marsRover = new MarsRover(0,0, Direction.N, marsMap);

        when(marsMap.checkInPit(any(Position.class))).thenReturn(false);

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.L);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.W);
        Assert.assertEquals(location.getPosition().getX(), 0);
        Assert.assertEquals(location.getPosition().getY(), 0);
    }

    @Test
    public void should_Rotate_R_90_single_instruction() {
        MarsRover marsRover = new MarsRover(0,0, Direction.N, marsMap);

        when(marsMap.checkInPit(any(Position.class))).thenReturn(false);

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.R);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.E);
        Assert.assertEquals(location.getPosition().getX(), 0);
        Assert.assertEquals(location.getPosition().getY(), 0);
    }

    @Test
    public void should_handle_multiple_instructions() {
        MarsRover marsRover = new MarsRover(0,0, Direction.N, marsMap);

        when(marsMap.checkInPit(any(Position.class))).thenReturn(false);

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.M);
        instructions.add(Instruction.M);
        instructions.add(Instruction.R);
        instructions.add(Instruction.M);
        instructions.add(Instruction.L);
        instructions.add(Instruction.M);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.N);
        Assert.assertEquals(location.getPosition().getX(), 1);
        Assert.assertEquals(location.getPosition().getY(), 3);
    }

    @Test
    public void should_handle_B_to_back() {
        MarsRover marsRover = new MarsRover(0,0, Direction.N, marsMap);

        when(marsMap.checkInPit(any(Position.class))).thenReturn(false);

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.B);
        instructions.add(Instruction.M);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.N);
        Assert.assertEquals(location.getPosition().getX(), 0);
        Assert.assertEquals(location.getPosition().getY(), -1);
    }

    @Test
    public void should_back_to_normal_when_get_second_B() {
        MarsRover marsRover = new MarsRover(0, 0, Direction.N, marsMap);

        when(marsMap.checkInPit(any(Position.class))).thenReturn(false);

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.B);
        instructions.add(Instruction.M);
        instructions.add(Instruction.B);
        instructions.add(Instruction.M);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.N);
        Assert.assertEquals(location.getPosition().getX(), 0);
        Assert.assertEquals(location.getPosition().getY(), 0);
    }

    @Test
    public void should_handle_B_With_R() {
        MarsRover marsRover = new MarsRover(0, 0, Direction.N, marsMap);

        when(marsMap.checkInPit(any(Position.class))).thenReturn(false);

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.B);
        instructions.add(Instruction.R);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.W);
    }

    @Test
    public void should_handle_B_with_L() {
        MarsRover marsRover = new MarsRover(0, 0, Direction.N, marsMap);

        when(marsMap.checkInPit(any(Position.class))).thenReturn(false);

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.B);
        instructions.add(Instruction.L);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getDirection(), Direction.E);
    }

    @Test
    public void should_check_if_position_is_in_pitList() {
        MarsRover marsRover = new MarsRover(0, 0, Direction.N, marsMap);;
        marsRover.addPit(new Position(2, 5));
        Assert.assertTrue(marsRover.checkInPit(new Position(2, 5)));
        Assert.assertFalse(marsRover.checkInPit(new Position(2, 4)));
    }

    @Test
    public void should_record_pit() {
        MarsRover marsRover = new MarsRover(0, 0, Direction.N, marsMap);
        when(marsMap.checkInPit(any(Position.class))).thenReturn(true);
        Assert.assertFalse(marsRover.checkInPit(new Position(0, 1)));
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.M);
        marsRover.execute(instructions);
        Assert.assertTrue(marsRover.checkInPit(new Position(0, 1)));
    }

    @Test
    public void should_regardless_of_pit_position_instruction() {
        MarsRover marsRover = new MarsRover(0, 0, Direction.N, marsMap);
        marsRover.addPit(new Position(-1, 1));
        when(marsMap.checkInPit(any(Position.class))).thenReturn(false);
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.M);
        instructions.add(Instruction.L);
        instructions.add(Instruction.M);
        instructions.add(Instruction.L);
        instructions.add(Instruction.M);
        instructions.add(Instruction.M);
        Location location = marsRover.execute(instructions);
        Assert.assertEquals(location.getPosition().getX(), 0);
        Assert.assertEquals(location.getPosition().getY(), -1);
    }
}
