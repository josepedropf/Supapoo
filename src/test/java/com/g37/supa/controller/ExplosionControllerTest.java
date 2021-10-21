package com.g37.supa.controller;

import com.g37.supa.controller.level.ExplosionController;
import com.g37.supa.controller.level.ZonkController;
import com.g37.supa.model.*;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Murphy;
import com.g37.supa.model.level.elements.Zonk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplosionControllerTest {
    private ExplosionController controller;
    private ZonkController zonk_controller;
    private Murphy murphy;
    private List<Zonk> zonks;
    private Level level;
    private TextImage textImage;

    @BeforeEach
    void setUp() throws IOException {
        textImage = new LoaderTextImageBuilder("test").createTextImage();
        level = new Level(new Size(50, 50), new Size(4, 9),false);

        murphy = new Murphy(new Position(10, 10), textImage);
        level.setMurphy(murphy);

        TextImage textImage = new LoaderTextImageBuilder("test").createTextImage();
        zonks = new ArrayList<>();
        zonks.add(new Zonk(new Position(10, 8), textImage));
        //zonks.add(new Zonk(new Position(1, 1), textImage));
        level.setZonks(zonks);

        controller = new ExplosionController(level);
        zonk_controller = new ZonkController(level);
    }

    @Test
    void firstExplosion() throws IOException {
        Map<Zonk, Position> zonk_map = new HashMap<>();
        for (Zonk zonk : level.getZonks())
            zonk_map.put(zonk, zonk.getPosition());
        zonk_controller.moveElements(zonk_map);
        zonk_controller.moveElements(zonk_map);
        assertEquals(true, level.isExplosion(new Position(10, 10)));
    }

    @Test
    void killMurphy() throws IOException {
        Map<Zonk, Position> zonk_map = new HashMap<>();
        for (Zonk zonk : level.getZonks())
            zonk_map.put(zonk, zonk.getPosition());
        zonk_controller.moveElements(zonk_map);
        zonk_controller.moveElements(zonk_map);
        controller.explode();
        assertEquals(0, level.getMurphy().getLife());
    }

    @Test
    void explosionExpansion() throws IOException {
        Map<Zonk, Position> zonk_map = new HashMap<>();
        for (Zonk zonk : level.getZonks())
            zonk_map.put(zonk, zonk.getPosition());
        zonk_controller.moveElements(zonk_map);
        zonk_controller.moveElements(zonk_map);
        controller.expand();
        assertEquals(true, level.isExplosion(new Position(9, 9)));
        assertEquals(true, level.isExplosion(new Position(9, 10)));
        assertEquals(true, level.isExplosion(new Position(9, 11)));
        assertEquals(true, level.isExplosion(new Position(10, 9)));
        assertEquals(true, level.isExplosion(new Position(10, 11)));
        assertEquals(true, level.isExplosion(new Position(11, 9)));
        assertEquals(true, level.isExplosion(new Position(11, 10)));
        assertEquals(true, level.isExplosion(new Position(11, 11)));
    }
}
