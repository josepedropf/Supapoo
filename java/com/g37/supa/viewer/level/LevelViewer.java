package com.g37.supa.viewer.level;

import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.Text;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.LevelElement;
import com.g37.supa.viewer.Viewer;

import java.util.EnumSet;
import java.util.List;

public class LevelViewer extends Viewer<Level> {
    private Position cameraOrigin;
    private Size visibleLevel;

    public LevelViewer(Level level) {
        super(level);
    }

    @Override
    public void drawModel(GUI gui) {
        visibleLevel = new Size((int) Math.ceil((double) gui.getSize().getWidth() / (double) getModel().getElementSize().getWidth()), (int) Math.ceil((double) gui.getSize().getHeight() / (double) getModel().getElementSize().getHeight()));
        int x, y;
        if (getModel().getMurphy().getPosition().getX() <= (visibleLevel.getWidth() - 1) / 2) {
            x = 0;
        } else if (getModel().getSize().getWidth() - 1 - getModel().getMurphy().getPosition().getX() <= visibleLevel.getWidth() / 2) {
            x = getModel().getSize().getWidth() - visibleLevel.getWidth();
        } else {
            x = getModel().getMurphy().getPosition().getX() - (visibleLevel.getWidth() - 1) / 2;
        }
        if (getModel().getMurphy().getPosition().getY() <= (visibleLevel.getHeight() - 1) / 2) {
            y = 0;
        } else if (getModel().getSize().getHeight() - 1 - getModel().getMurphy().getPosition().getY() <= visibleLevel.getHeight() / 2) {
            y = getModel().getSize().getHeight() - visibleLevel.getHeight();
        } else {
            y = getModel().getMurphy().getPosition().getY() - (visibleLevel.getHeight() - 1) / 2;
        }
        cameraOrigin = new Position(x, y);
        LevelElementViewer levelElementViewer = new LevelElementViewer();
        drawElements(getModel().getWalls(), levelElementViewer, gui);
        drawElements(getModel().getInfotrons(), levelElementViewer, gui);
        drawElements(getModel().getEndBlocks(), levelElementViewer, gui);
        drawElements(getModel().getBases(), levelElementViewer, gui);
        drawElements(getModel().getZonks(), levelElementViewer, gui);
        drawElements(getModel().getXEnemies(), levelElementViewer, gui);
        drawElements(getModel().getJokers(), levelElementViewer, gui);
        drawElements(getModel().getPorts(), levelElementViewer, gui);
        drawElement(getModel().getMurphy(), levelElementViewer, gui);
        drawElements(getModel().getExplosions(), levelElementViewer, gui);

        gui.drawText(new Position(0, gui.getSize().getHeight() - 1), new Text("Infotrons: " + getModel().getInfotrons().size(), "#000000", "#ffffff", EnumSet.of(Text.TEXTMODIFIER.BOLD)));
    }

    private <T extends LevelElement> void drawElements(List<T> elements, LevelElementViewer viewer, GUI gui) {
        for (T element : elements)
            drawElement(element, viewer, gui);
    }

    private <T extends LevelElement> void drawElement(T element, LevelElementViewer viewer, GUI gui) {
        if (element.getPosition().getX() >= cameraOrigin.getX()
                && element.getPosition().getX() < cameraOrigin.getX() + visibleLevel.getWidth()
                && element.getPosition().getY() >= cameraOrigin.getY()
                && element.getPosition().getY() < cameraOrigin.getY() + visibleLevel.getHeight()) {
            Position drawPosition = new Position((element.getPosition().getX() - cameraOrigin.getX()) * getModel().getElementSize().getWidth(),
                    (element.getPosition().getY() - cameraOrigin.getY()) * getModel().getElementSize().getHeight());
            viewer.drawElement(element, gui, drawPosition);
        }
    }
}
