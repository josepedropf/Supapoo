package com.g37.supa.controller;

import com.g37.supa.Game;
import com.g37.supa.controller.menu.MenuController;
import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.menu.Menu;
import com.g37.supa.model.menu.LevelMenuBuilder;
import com.g37.supa.model.menu.PauseMenuBuilder;
import com.g37.supa.model.menu.StartMenuBuilder;
import com.g37.supa.model.menu.elements.Button;
import com.g37.supa.state.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MenuControllerTest {
    private Game game;
    private MenuController level_controller;
    private MenuController start_controller;
    private MenuController pause_controller;
    private TextImage textImage;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        textImage = new LoaderTextImageBuilder("test").createTextImage();
        game = new Game(5);
        level_controller = new MenuController(new LevelMenuBuilder(new Size(50, 50)).createMenu());
        start_controller = new MenuController(new StartMenuBuilder(new Size(50, 50)).createMenu());
        pause_controller = new MenuController(new PauseMenuBuilder(new Size(50, 50)).createMenu());
    }

    @Test
    void menuDown() throws IOException {
        start_controller.getModel().setSelectedButton(0);
        start_controller.step(game, GUI.KEYACTION.DOWN, 10);
        assertEquals(1, start_controller.getModel().getSelectedButtonNumber());
        start_controller.getModel().setSelectedButton(start_controller.getModel().getButtons().size() - 1);
        start_controller.step(game, GUI.KEYACTION.DOWN, 20);
        assertEquals(0, start_controller.getModel().getSelectedButtonNumber());
    }

    @Test
    void menuUp() throws IOException {
        start_controller.getModel().setSelectedButton(1);
        start_controller.step(game, GUI.KEYACTION.UP, 10);
        assertEquals(0, start_controller.getModel().getSelectedButtonNumber());
        start_controller.getModel().setSelectedButton(0);
        start_controller.step(game, GUI.KEYACTION.UP, 20);
        assertEquals(start_controller.getModel().getButtons().size() - 1, start_controller.getModel().getSelectedButtonNumber());
    }

    @Test
    void menuClose() throws IOException {
        start_controller.step(game, GUI.KEYACTION.CLOSE, 10);
        assertEquals(null, game.getStates().peek());
    }

    @Test
    void menuStart() throws IOException {
        start_controller.getModel().setSelectedButton(0);
        start_controller.step(game, GUI.KEYACTION.SELECT, 10);
        assertEquals(1, game.getCurrentLevel());
        assertEquals(2, game.getStates().size());
    }

    @Test
    void menuContinue() throws IOException {
        game.pushState(new MenuState(new Menu()));
        pause_controller.getModel().setSelectedButton(0);
        int state_no = game.getStates().size();
        pause_controller.step(game, GUI.KEYACTION.SELECT, 10);
        assertEquals(state_no - 1, game.getStates().size());
    }

    @Test
    void menuMain() throws IOException {
        game.pushState(new MenuState(new Menu()));
        game.pushState(new MenuState(new Menu()));
        pause_controller.getModel().setSelectedButton(1);
        int state_no = game.getStates().size();
        pause_controller.step(game, GUI.KEYACTION.SELECT, 10);
        assertEquals(state_no - 2, game.getStates().size());
    }

    @Test
    void menuMinus() throws IOException {
        game.setCurrentLevel(3);
        level_controller.getModel().setSelectedButton(2);
        level_controller.step(game, GUI.KEYACTION.SELECT, 10);
        assertEquals(2, game.getCurrentLevel());
        assertEquals("02", level_controller.getModel().getButtons().get(1).getText().getString());
    }

    @Test
    void menuPlus() throws IOException {
        game.setCurrentLevel(3);
        level_controller.getModel().setSelectedButton(0);
        level_controller.step(game, GUI.KEYACTION.SELECT, 10);
        assertEquals(4, game.getCurrentLevel());
        assertEquals("04", level_controller.getModel().getButtons().get(1).getText().getString());
    }

    @Test
    void menuSelectLevel() throws IOException {
        game.setCurrentLevel(3);
        start_controller.getModel().setSelectedButton(1);
        int state_no = game.getStates().size();
        start_controller.step(game, GUI.KEYACTION.SELECT, 10);
        assertEquals(1, game.getCurrentLevel());
        assertEquals(state_no + 1, game.getStates().size());
    }

    @Test
    void menuLevelSelect() throws IOException {
        game.setCurrentLevel(3);
        int state_no = game.getStates().size();
        level_controller.getModel().setSelectedButton(2);
        level_controller.step(game, GUI.KEYACTION.SELECT, 10);
        level_controller.getModel().setSelectedButton(1);
        level_controller.step(game, GUI.KEYACTION.SELECT, 20);
        assertEquals(2, game.getCurrentLevel());
        assertEquals(state_no, game.getStates().size());
    }

    @Test
    void menuQuit() throws IOException {
        start_controller.getModel().setSelectedButton(2);
        start_controller.step(game, GUI.KEYACTION.SELECT, 10);
        assertEquals(null, game.getStates().peek());
    }
}
