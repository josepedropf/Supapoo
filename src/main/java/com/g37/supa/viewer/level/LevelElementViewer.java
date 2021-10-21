package com.g37.supa.viewer.level;

import com.g37.supa.model.Position;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.level.elements.LevelElement;

public class LevelElementViewer {
    public void drawElement(LevelElement element, GUI gui, Position position) {
        gui.drawTextImage(position, element.getTextImage());
    }
}
