package com.g37.supa.controller;

import com.g37.supa.Game;
import com.g37.supa.model.gui.GUI;

import java.io.IOException;

public abstract class Controller<T> {
    private T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return this.model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public abstract void step(Game game, GUI.KEYACTION action, long time) throws IOException;
}
