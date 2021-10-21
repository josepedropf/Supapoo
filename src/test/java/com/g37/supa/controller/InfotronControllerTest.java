package com.g37.supa.controller;

import com.g37.supa.controller.level.InfotronController;
import com.g37.supa.model.*;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Infotron;
import com.g37.supa.model.level.elements.Murphy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfotronControllerTest {
    private InfotronController controller;
    private Murphy murphy;
    private List<Infotron> infotrons;
    private Level level;
    private TextImage textImage;

    @BeforeEach
    void setUp() throws IOException {
        TextImage textImage = new LoaderTextImageBuilder("test").createTextImage();
        level = new Level(new Size(50, 50), new Size(4, 9),false);

        murphy = new Murphy(new Position(10, 10), textImage);
        level.setMurphy(murphy);

        infotrons = new ArrayList<>();
        infotrons.add(new Infotron(new Position(1, 1), textImage));
        infotrons.add(new Infotron(new Position(4, level.getSize().getHeight() - 2), textImage));
        infotrons.add(new Infotron(new Position(5, level.getSize().getHeight() - 2), textImage));
        infotrons.add(new Infotron(new Position(4, level.getSize().getHeight() - 1), textImage));
        infotrons.add(new Infotron(new Position(5, level.getSize().getHeight() - 1), textImage));
        level.setInfotrons(infotrons);

        controller = new InfotronController(level);
    }

    @Test
    void moveZonkGravity() throws IOException {
        Map<Infotron, Position> infotron_map = new HashMap<>();
        for (Infotron infotron : level.getInfotrons())
            infotron_map.put(infotron, infotron.getPosition());
        controller.moveElements(infotron_map);
        assertEquals(new Position(1, 2), infotrons.get(0).getPosition());
    }

    @Test
    void slideZonkLeft() throws IOException {
        Map<Infotron, Position> infotron_map = new HashMap<>();
        for (Infotron infotron : level.getInfotrons())
            infotron_map.put(infotron, infotron.getPosition());
        controller.moveElements(infotron_map);
        assertEquals(new Position(3, level.getSize().getHeight() - 1), infotrons.get(1).getPosition());
    }

    @Test
    void slideZonkRight() throws IOException {
        Map<Infotron, Position> infotron_map = new HashMap<>();
        for (Infotron infotron : level.getInfotrons())
            infotron_map.put(infotron, infotron.getPosition());
        controller.moveElements(infotron_map);
        assertEquals(new Position(6, level.getSize().getHeight() - 1), infotrons.get(2).getPosition());
    }
}
