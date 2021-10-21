package com.g37.supa.viewer;

import com.g37.supa.model.gui.GUI;

import java.io.IOException;

abstract public class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public void draw(GUI gui) throws IOException {
        gui.clear();
        drawModel(gui);
        gui.refresh();
    }

    protected abstract void drawModel(GUI gui);

    public T getModel() {
        return model;
    }
}
