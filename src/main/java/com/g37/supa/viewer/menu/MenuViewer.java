package com.g37.supa.viewer.menu;

import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.menu.Menu;
import com.g37.supa.viewer.Viewer;

import java.util.List;

public class MenuViewer extends Viewer<Menu> {

    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawModel(GUI gui) {
        ImageViewer imageViewer = new ImageViewer();
        ButtonViewer buttonViewer = new ButtonViewer();
        TitleViewer titleViewer = new TitleViewer();
        drawElements(getModel().getImages(), imageViewer, gui);
        drawElements(getModel().getButtons(), buttonViewer, gui);
        drawElements(getModel().getTitles(), titleViewer, gui);
    }

    public <T> void drawElements(List<T> elements, MenuElementViewer<T> viewer, GUI gui) {
        for (T element : elements) {
            viewer.drawElement(element, gui);
        }
    }
}
