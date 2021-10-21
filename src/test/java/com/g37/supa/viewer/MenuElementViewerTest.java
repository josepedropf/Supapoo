package com.g37.supa.viewer;

import com.g37.supa.model.*;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.menu.elements.Button;
import com.g37.supa.model.menu.elements.Image;
import com.g37.supa.model.menu.elements.Title;
import com.g37.supa.viewer.menu.ButtonViewer;
import com.g37.supa.viewer.menu.ImageViewer;
import com.g37.supa.viewer.menu.TitleViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuElementViewerTest {
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawButton() {
        ButtonViewer viewer = new ButtonViewer();
        Text text = new Text("TEST", "#ffffff", "#ffffff");
        Button button = new Button(new Position(1, 1), text, text, new Size(10, 5), Button.BUTTONACTION.START);
        Position textPosition = new Position(button.getPosition().getX() + button.getSize().getWidth() / 2 - button.getHoverText().getString().length() / 2, button.getPosition().getY() + button.getSize().getHeight() / 2);
        viewer.drawElement(button, gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(textPosition, text);
        Mockito.verify(gui, Mockito.times(1)).drawRectangle(new Position(1, 1), new Size(10, 5), new Appearance(' ', "#000000", "#ffffff"));
    }

    @Test
    void drawImage() throws IOException {
        ImageViewer viewer = new ImageViewer();
        TextImage textImage = new LoaderTextImageBuilder("test").createTextImage();
        Image image = new Image(new Position(1, 1), textImage);
        viewer.drawElement(image, gui);
        Mockito.verify(gui, Mockito.times(1)).drawTextImage(new Position(1, 1), textImage);
    }

    @Test
    void drawTitle() {
        TitleViewer viewer = new TitleViewer();
        Text text = new Text("TEST", "#ffffff","ffffff");
        Title title = new Title(new Position(1, 1), text);
        viewer.drawElement(title, gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(1, 1), text);
    }
}
