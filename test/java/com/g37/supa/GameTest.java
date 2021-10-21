package com.g37.supa;

import com.g37.supa.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    private Game game;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        game = new Game(5);
    }

    @Test
    void gameStart() throws IOException {
        game.start();
        assertEquals(1, game.getCurrentLevel());
        assertEquals(2, game.getStates().size());
    }

    @Test
    void gamePause() throws IOException {
        game.start();
        State s = game.getStates().peek();
        game.pause();
        assertEquals(3, game.getStates().size());
        game.popState();
        assertEquals(s, game.getStates().peek());
    }

    @Test
    void gameSelectLevel() throws IOException {
        game.selectLevel();
        assertEquals(1, game.getCurrentLevel());
        assertEquals(2, game.getStates().size());
    }

    @Test
    void gameSkipLevel() throws IOException {
        game.setCurrentLevel(3);
        game.play();
        game.skipLevel();
        assertEquals(4, game.getCurrentLevel());
        assertEquals(2, game.getStates().size());
    }

    @Test
    void gameNextLevel() throws IOException {
        game.setCurrentLevel(3);
        game.play();
        game.nextLevel();
        assertEquals(4, game.getCurrentLevel());
        assertEquals(3, game.getStates().size());
    }

    @Test
    void gameLost() throws IOException {
        game.setCurrentLevel(3);
        game.play();
        game.lost();
        assertEquals(3, game.getCurrentLevel());
        assertEquals(2, game.getStates().size());
    }

    @Test
    void gamePlay() throws IOException {
        game.setCurrentLevel(3);
        game.play();
        assertEquals(3, game.getCurrentLevel());
        assertEquals(2, game.getStates().size());
    }

    @Test
    void gamePlayLevel() throws IOException {
        game.playLevel(4);
        assertEquals(4, game.getCurrentLevel());
        assertEquals(2, game.getStates().size());
    }

    @Test
    void gameRestart() throws IOException {
        game.setCurrentLevel(3);
        game.restartLevel();
        assertEquals(3, game.getCurrentLevel());
        assertEquals(1, game.getStates().size());
    }
}

