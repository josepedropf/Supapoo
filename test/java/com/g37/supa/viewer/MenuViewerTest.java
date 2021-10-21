package com.g37.supa.viewer;

import com.g37.supa.model.*;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.menu.Menu;
import com.g37.supa.model.menu.elements.Button;
import com.g37.supa.model.menu.elements.Image;
import com.g37.supa.model.menu.elements.Title;
import com.g37.supa.viewer.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MenuViewerTest {
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawMenu() throws IOException {
        Size size = new Size(50, 50);

        List<Button> buttons = new ArrayList<>();
        String color = "#ffbb00";
        String colorHigh = "#0088ff";
        String background = "#000000";
        Text continueText = new Text("CONTINUE", color, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text continueHighText = new Text("CONTINUE", colorHigh, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Button start_button = new Button(new Position(size.getWidth() / 2 - 6, size.getHeight() / 2 + 2), continueText, continueHighText, new Size(12, 3), Button.BUTTONACTION.CONTINUE);
        buttons.add(start_button);

        List<Title> titles = new ArrayList<>();
        Title title = new Title(new Position(size.getWidth() / 2 - 3, size.getHeight() / 8), new Text("TEST", "#000000", "#000000"));
        titles.add(title);

        List<Image> images = new ArrayList<>();
        TextImage titleTextImage = new LoaderTextImageBuilder("supa").createTextImage();
        Image titleImage = new Image(new Position(size.getWidth() / 2 - titleTextImage.getSize().getWidth() / 2, size.getHeight() / 2 - 11), titleTextImage);
        images.add(titleImage);

        Menu menu = new Menu();
        menu.setButtons(buttons);
        menu.setImages(images);
        menu.setTitles(titles);

        MenuViewer viewer = new MenuViewer(menu);
        viewer.drawModel(gui);

        Mockito.verify(gui, Mockito.times(1)).drawTextImage(titleImage.getPosition(), titleTextImage);

        Mockito.verify(gui, Mockito.times(1)).drawRectangle(start_button.getPosition(), start_button.getSize(), new Appearance(' ', "#000000", colorHigh));
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(start_button.getPosition().getX() + start_button.getSize().getWidth() / 2 - start_button.getHoverText().getString().length() / 2, start_button.getPosition().getY() + start_button.getSize().getHeight() / 2), start_button.getHoverText());

        Mockito.verify(gui, Mockito.times(1)).drawText(title.getPosition(), title.getText());
    }
}

