package com.g37.supa.controller;

import com.g37.supa.Game;
import com.g37.supa.controller.level.LevelController;
import com.g37.supa.controller.menu.SplashMenuController;
import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.LoaderLevelBuilder;
import com.g37.supa.model.level.elements.Infotron;
import com.g37.supa.model.menu.Menu;
import com.g37.supa.state.SplashMenuState;
import com.g37.supa.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LevelControllerTest {
    private Game game;
    private LevelController controller;
    private List<Infotron> infotrons;
    private TextImage textImage;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        textImage = new LoaderTextImageBuilder("test").createTextImage();
        game = new Game(5);
        game.play();
        controller = new LevelController(new LoaderLevelBuilder(game.getCurrentLevel()).createLevel());
        infotrons = new ArrayList<>();
    }

    @Test
    void levelQuit() throws IOException {
        controller.step(game, GUI.KEYACTION.QUIT, 50);
        assertEquals(1, game.getStates().size());
    }

    @Test
    void levelClose() throws IOException {
        controller.step(game, GUI.KEYACTION.CLOSE, 50);
        assertEquals(null, game.getStates().peek());
    }

    @Test
    void levelPause() throws IOException {
        State s = game.getStates().peek();
        controller.step(game, GUI.KEYACTION.PAUSE, 50);
        assertEquals(3, game.getStates().size());
        game.popState();
        assertEquals(s, game.getStates().peek());
    }

    @Test
    void levelSkip() throws IOException {
        controller.step(game, GUI.KEYACTION.SKIP, 50);
        assertEquals(2, game.getStates().size());
        assertEquals(2, game.getCurrentLevel());
    }

    @Test
    void levelRestart() throws IOException {
        controller.step(game, GUI.KEYACTION.RESTART, 50);
        assertEquals(2, game.getStates().size());
        assertEquals(1, game.getCurrentLevel());
    }

    @Test
    void levelEndWin() throws IOException {
        controller.getModel().getMurphy().setEndIntention(false);
        infotrons.add(new Infotron(new Position(1, 1), textImage));
        controller.getModel().setInfotrons(infotrons);
        controller.step(game, GUI.KEYACTION.NONE, 50);
        assertEquals(1, game.getCurrentLevel());
        controller.getModel().getMurphy().setEndIntention(false);
        controller.getModel().setInfotrons(new ArrayList<>());
        controller.step(game, GUI.KEYACTION.NONE, 50);
        assertEquals(1, game.getCurrentLevel());

        controller.getModel().getMurphy().setEndIntention(true);
        controller.getModel().setInfotrons(infotrons);
        controller.step(game, GUI.KEYACTION.NONE, 60);
        assertEquals(1, game.getCurrentLevel());
        controller.getModel().getMurphy().setEndIntention(true);
        controller.getModel().setInfotrons(new ArrayList<>());
        controller.step(game, GUI.KEYACTION.NONE, 70);
        assertEquals(2, game.getCurrentLevel());

    }

    @Test
    void levelEndLoose() throws IOException {
        State s = game.getStates().peek();
        controller.getModel().getMurphy().setLife(0);
        controller.step(game, GUI.KEYACTION.NONE, 50);
        assertEquals(s, game.getStates().peek());
        controller.step(game, GUI.KEYACTION.NONE, 100);
        assertEquals(s, game.getStates().peek());
        controller.step(game, GUI.KEYACTION.NONE, 1000);
        assertNotEquals(s, game.getStates().peek());
    }


}