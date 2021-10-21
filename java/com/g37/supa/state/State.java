package com.g37.supa.state;

import com.g37.supa.Game;
import com.g37.supa.controller.Controller;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.viewer.Viewer;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.KEYACTION action = gui.getNextAction();
        controller.step(game, action, time);
        viewer.draw(gui);
    }
}