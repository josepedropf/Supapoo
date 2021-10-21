package com.g37.supa.controller;

import com.g37.supa.controller.level.XEnemyController;
import com.g37.supa.model.*;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Murphy;
import com.g37.supa.model.level.elements.Wall;
import com.g37.supa.model.level.elements.XEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XEnemyControllerTest {
    private XEnemyController controller;
    private Murphy murphy;
    private List<XEnemy> xEnemies;
    private Level level;
    private TextImage textImage;

    @BeforeEach
    void setUp() throws IOException {
        textImage = new LoaderTextImageBuilder("test").createTextImage();
        level = new Level(new Size(50, 50), new Size(1, 1), false);

        murphy = new Murphy(new Position(1, 1), textImage);
        level.setMurphy(murphy);

        xEnemies = new ArrayList<>();
        xEnemies.add(new XEnemy(new Position(1, 1), textImage));
        xEnemies.add(new XEnemy(new Position(level.getSize().getWidth() - 2, 1), textImage));
        level.setXEnemies(xEnemies);

        level.setWalls(Arrays.asList(new Wall(new Position(level.getSize().getWidth() - 1, 1), textImage)));
        level.setInfotrons(Arrays.asList());
        level.setBases(Arrays.asList());
        level.setZonks(Arrays.asList());
        level.setEndBlocks(Arrays.asList());

        controller = new XEnemyController(level);
    }

    @Test
    void slideXEnemyRight() {
        controller.moveXEnemies();
        assertEquals(new Position(2, 1), xEnemies.get(0).getPosition());
    }

    @Test
    void slideXEnemyLeft() {
        controller.moveXEnemies();
        System.out.println("" + xEnemies.get(1).getPosition().getX() + xEnemies.get(1).getPosition().getY());
        assertEquals(new Position(level.getSize().getWidth() - 3, 1), xEnemies.get(1).getPosition());
    }
}
