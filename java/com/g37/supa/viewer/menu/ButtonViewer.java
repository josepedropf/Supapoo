package com.g37.supa.viewer.menu;

import com.g37.supa.model.Appearance;
import com.g37.supa.model.Position;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.menu.elements.Button;

public class ButtonViewer implements MenuElementViewer<Button> {
    @Override
    public void drawElement(Button button, GUI gui) {
        Position textPosition;
        if (button.getHover()) {
            gui.drawRectangle(button.getPosition(), button.getSize(), new Appearance(' ', "#000000", button.getHoverText().getBackgroundColor()));
            textPosition = new Position(button.getPosition().getX() + button.getSize().getWidth() / 2 - button.getHoverText().getString().length() / 2, button.getPosition().getY() + button.getSize().getHeight() / 2);
            gui.drawText(textPosition, button.getHoverText());
        } else {
            gui.drawRectangle(button.getPosition(), button.getSize(), new Appearance(' ', "#000000", button.getText().getBackgroundColor()));
            textPosition = new Position(button.getPosition().getX() + button.getSize().getWidth() / 2 - button.getText().getString().length() / 2, button.getPosition().getY() + button.getSize().getHeight() / 2);
            gui.drawText(textPosition, button.getText());
        }
    }
}
