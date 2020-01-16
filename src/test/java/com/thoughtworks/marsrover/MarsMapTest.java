package com.thoughtworks.marsrover;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class MarsMapTest {

    @Test
    public void should_init_map_with_empty_pit() {
        MarsMap marsMap = new MarsMap();
        Assert.assertEquals(marsMap.getPitList(), Collections.emptyList());
    }

    @Test
    public void should_check_if_position_is_in_pitList() {
        MarsMap marsMap = new MarsMap();
        marsMap.addPit(new Position(2, 5));
        Assert.assertTrue(marsMap.checkInPit(new Position(2, 5)));
        Assert.assertFalse(marsMap.checkInPit(new Position(2, 4)));
    }
}
