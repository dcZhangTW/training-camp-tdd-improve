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

}
