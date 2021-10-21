package com.g37.supa.viewer.menu;

import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.menu.elements.Image;

public class ImageViewer implements MenuElementViewer<Image> {
    @Override
    public void drawElement(Image element, GUI gui) {
        gui.drawTextImage(element.getPosition(), element.getTextImage());
    }
}
