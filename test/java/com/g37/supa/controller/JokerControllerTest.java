package com.g37.supa.controller;

import com.g37.supa.controller.level.JokerController;
import com.g37.supa.model.*;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Joker;
import com.g37.supa.model.level.elements.Murphy;
import com.g37.supa.model.level.elements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class JokerControllerTest {
    private JokerController controller;
    private Murphy murphy;
    private List<Joker> jokers;
    private List<Wall> walls;
    private Level level;
    private TextImage textImage;

    @BeforeEach
    void setUp() throws IOException {
        textImage = new LoaderTextImageBuilder("test").createTextImage();
        level = new Level(new Size(50, 50), new Size(1, 1), false);

        murphy = new Murphy(new Position(1, 1), textImage);
        level.setMurphy(murphy);

        jokers = new ArrayList<>();
        jokers.add(new Joker(new Position(10, 10), textImage));

        walls = new ArrayList<>();

        level.setJokers(jokers);

        controller = new JokerController(level);
    }

    @Test
    void moveJoker() {
        controller.moveJokers();
        assertNotEquals(new Position(10, 10), jokers.get(0).getPosition());
    }

    @Test
    void moveJokersGap() {
        walls.add(new Wall(new Position(10, 9), textImage));
        walls.add(new Wall(new Position(9, 10), textImage));
        walls.add(new Wall(new Position(11, 10), textImage));

        level.setWalls(walls);
        controller.moveJokers();

        assertEquals(new Position(10, 11), jokers.get(0).getPosition());
    }

    @Test
    void moveJokersClosed() {
        walls.add(new Wall(new Position(10, 9), textImage));
        walls.add(new Wall(new Position(9, 10), textImage));
        walls.add(new Wall(new Position(11, 10), textImage));
        walls.add(new Wall(new Position(10, 11), textImage));

        level.setWalls(walls);
        controller.moveJokers();

        assertEquals(new Position(10, 10), jokers.get(0).getPosition());
    }
}

