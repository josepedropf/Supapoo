package com.g37.supa;

import com.g37.supa.model.Size;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.gui.LanternaGUI;
import com.g37.supa.model.level.LoaderLevelBuilder;
import com.g37.supa.model.menu.*;
import com.g37.supa.state.LevelState;
import com.g37.supa.state.MenuState;
import com.g37.supa.state.SplashMenuState;
import com.g37.supa.state.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Stack;

public class Game {
    private final GUI gui;
    private final StartMenuBuilder startMenuBuilder;
    private final PauseMenuBuilder pauseMenuBuilder;
    private final WinMenuBuilder winMenuBuilder;
    private final LevelMenuBuilder levelMenuBuilder;
    private final LostMenuBuilder lostMenuBuilder;
    private final int totalLevels;
    private final Stack<State> states;
    private int currentLevel;

    public Game(int totalLevels) throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(new Size(150, 45));
        this.startMenuBuilder = new StartMenuBuilder(gui.getSize());
        this.pauseMenuBuilder = new PauseMenuBuilder(gui.getSize());
        this.winMenuBuilder = new WinMenuBuilder(gui.getSize());
        this.levelMenuBuilder = new LevelMenuBuilder(gui.getSize());
        this.lostMenuBuilder = new LostMenuBuilder(gui.getSize());
        this.totalLevels = totalLevels;
        this.currentLevel = 1;
        this.states = new Stack<>();
        this.states.push(new MenuState(startMenuBuilder.createMenu()));
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        new Game(5).run();
    }

    public void pushState(State state) {
        states.push(state);
    }

    public void popState() {
        states.pop();
    }

    public void pause() throws IOException {
        states.push(new MenuState(pauseMenuBuilder.createMenu()));
    }

    public void selectLevel() throws IOException {
        currentLevel = 1;
        states.push(new MenuState(levelMenuBuilder.createMenu()));
    }

    public void nextLevel() throws IOException {
        skipLevel();
        states.push(new SplashMenuState(winMenuBuilder.createMenu()));
    }

    public void skipLevel() throws IOException {
        states.pop();
        currentLevel++;
        if (currentLevel >= totalLevels + 1) {
            currentLevel = 1;
            this.states.push(new SplashMenuState(winMenuBuilder.createMenu()));
            return;
        }
        states.push(new LevelState(new LoaderLevelBuilder(currentLevel).createLevel()));
    }

    public void lost() throws IOException {
        states.pop();
        states.push(new SplashMenuState(lostMenuBuilder.createMenu()));
    }

    public void start() throws IOException {
        currentLevel = 1;
        states.push(new LevelState(new LoaderLevelBuilder(currentLevel).createLevel()));
    }

    public void play() throws IOException {
        states.push(new LevelState(new LoaderLevelBuilder(currentLevel).createLevel()));
    }

    public void playLevel(int level) throws IOException {
        if (level < 1) level = 1;
        if (level > totalLevels) level = totalLevels;
        currentLevel = level;
        states.push(new LevelState(new LoaderLevelBuilder(level).createLevel()));
    }

    public void restartLevel() throws IOException {
        states.pop();
        states.push(new LevelState(new LoaderLevelBuilder(currentLevel).createLevel()));
    }

    private void run() throws IOException {
        int FPS = 25;
        int frameTime = 1000 / FPS;

        while (this.states.peek() != null) {
            long startTime = System.currentTimeMillis();

            states.peek().step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
        gui.close();
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int level) {
        if (level < 1) level = 1;
        if (level > totalLevels) level = totalLevels;
        this.currentLevel = level;
    }

    public int getTotalLevels() {
        return totalLevels;
    }

    public Stack<State> getStates() {
        return states;
    }

    public void incrementLevel() {
        if (currentLevel < totalLevels)
            currentLevel++;
    }

    public void decrementLevel() {
        if (currentLevel > 1)
            currentLevel--;
    }
}

