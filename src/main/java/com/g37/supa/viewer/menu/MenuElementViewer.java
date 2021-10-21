package com.g37.supa.viewer.menu;

import com.g37.supa.model.gui.GUI;

public interface MenuElementViewer<T> {
    void drawElement(T element, GUI gui);
}