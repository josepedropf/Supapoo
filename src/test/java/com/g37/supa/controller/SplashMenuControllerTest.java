package com.g37.supa.controller;

import com.g37.supa.Game;
import com.g37.supa.controller.menu.SplashMenuController;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.menu.Menu;
import com.g37.supa.state.SplashMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplashMenuControllerTest {
    private Game game;
    SplashMenuController controller;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        game = new Game(5);
        game.pushState(new SplashMenuState(new Menu()));
        controller = new SplashMenuController(new Menu(), 100);
    }

    @Test
    void updateStartTime() throws IOException {
        controller.step(game, GUI.KEYACTION.NONE, 50);
        assertEquals(50, controller.getStartTime());
    }

    @Test
    void splashEnd() throws IOException {
        controller.step(game, GUI.KEYACTION.NONE, 50);
        controller.step(game, GUI.KEYACTION.NONE, 200);
        assertEquals(1, game.getStates().size());
    }

}
