package com.g37.supa.controller;

import com.g37.supa.controller.level.MurphyController;
import com.g37.supa.model.*;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Murphy;
import com.g37.supa.model.level.elements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MurphyControllerTest {
    private MurphyController controller;
    private Murphy murphy;
    private Level level;
    private TextImage textImage;

    @BeforeEach
    void setUp() throws IOException {
        textImage = new LoaderTextImageBuilder("test").createTextImage();
        level = new Level(new Size(50, 50), new Size(10, 5), false);

        murphy = new Murphy(new Position(10, 10), textImage);
        level.setMurphy(murphy);
        controller = new MurphyController(level);
    }

    @Test
    void moveMurphyRightEmpty() {
        controller.moveMurphyRight();
        assertEquals(new Position(11, 10), murphy.getPosition());
    }

    @Test
    void moveMurphyRightNotEmpty() {
        level.setWalls(Arrays.asList(new Wall(new Position(11, 10), textImage)));
        controller.moveMurphyRight();
        assertEquals(new Position(10, 10), murphy.getPosition());
    }
}
