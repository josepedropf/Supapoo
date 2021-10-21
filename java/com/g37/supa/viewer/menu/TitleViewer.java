package com.g37.supa.viewer.menu;

import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.menu.elements.Title;

public class TitleViewer implements MenuElementViewer<Title> {
    @Override
    public void drawElement(Title title, GUI gui) {
        gui.drawText(title.getPosition(), title.getText());
    }
}
