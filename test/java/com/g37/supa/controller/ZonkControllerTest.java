package com.g37.supa.controller;

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

public class ZonkControllerTest {
    private ZonkController controller;
    private Murphy murphy;
    private List<Zonk> zonks;
    private Level level;
    private TextImage textImage;

    @BeforeEach
    void setUp() throws IOException {
        textImage = new LoaderTextImageBuilder("test").createTextImage();
        level = new Level(new Size(50, 50), new Size(9, 4),false);

        murphy = new Murphy(new Position(10, 10), textImage);
        level.setMurphy(murphy);

        zonks = new ArrayList<>();
        zonks.add(new Zonk(new Position(1, 1), textImage));
        zonks.add(new Zonk(new Position(4, level.getSize().getHeight() - 2), textImage));
        zonks.add(new Zonk(new Position(5, level.getSize().getHeight() - 2), textImage));
        zonks.add(new Zonk(new Position(4, level.getSize().getHeight() - 1), textImage));
        zonks.add(new Zonk(new Position(5, level.getSize().getHeight() - 1), textImage));
        level.setZonks(zonks);

        controller = new ZonkController(level);
    }

    @Test
    void moveZonkGravity() throws IOException {
        Map<Zonk, Position> zonks_map = new HashMap<>();
        for (Zonk zonk : level.getZonks())
            zonks_map.put(zonk, zonk.getPosition());
        controller.moveElements(zonks_map);
        assertEquals(new Position(1, 2), zonks.get(0).getPosition());
    }

    @Test
    void slideZonkLeft() throws IOException {
        Map<Zonk, Position> zonks_map = new HashMap<>();
        for (Zonk zonk : level.getZonks())
            zonks_map.put(zonk, zonk.getPosition());
        controller.moveElements(zonks_map);
        assertEquals(new Position(3, level.getSize().getHeight() - 1), zonks.get(1).getPosition());
    }

    @Test
    void slideZonkRight() throws IOException {
        Map<Zonk, Position> zonks_map = new HashMap<>();
        for (Zonk zonk : level.getZonks())
            zonks_map.put(zonk, zonk.getPosition());
        controller.moveElements(zonks_map);
        assertEquals(new Position(6, level.getSize().getHeight() - 1), zonks.get(2).getPosition());
    }
}
