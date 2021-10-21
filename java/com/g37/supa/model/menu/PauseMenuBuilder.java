package com.g37.supa.model.menu;

import com.g37.supa.model.*;
import com.g37.supa.model.menu.elements.Button;
import com.g37.supa.model.menu.elements.Image;
import com.g37.supa.model.menu.elements.Title;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class PauseMenuBuilder extends MenuBuilder {
    public PauseMenuBuilder(Size size) {
        super(size);
    }

    @Override
    protected List<Title> createTitles() {
        //List<Title> titles = new ArrayList<>();
        //Title title = new Title(new Position(size.getWidth() / 2 - 3, size.getHeight() / 8), new Text("PAUSED", "#000000", "#ff2222"));
        //titles.add(title);
        return new ArrayList<>();
    }

    @Override
    protected List<Button> createButtons() {
        List<Button> buttons = new ArrayList<>();
        String color = "#ffbb00";
        String colorHigh = "#0088ff";
        String background = "#000000";
        Text continueText = new Text("CONTINUE", color, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text continueHighText = new Text("CONTINUE", colorHigh, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text quitText = new Text("QUIT", color, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text quitHighText = new Text("QUIT", colorHigh, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Button start_button = new Button(new Position(size.getWidth() / 2 - 6, size.getHeight() / 2 + 2), continueText, continueHighText, new Size(12, 3), Button.BUTTONACTION.CONTINUE);
        Button quit_button = new Button(new Position(size.getWidth() / 2 - 6, size.getHeight() / 2 + 6), quitText, quitHighText, new Size(12, 3), Button.BUTTONACTION.MAIN);
        buttons.add(start_button);
        buttons.add(quit_button);
        return buttons;
    }

    @Override
    protected List<Image> createImages() throws IOException {
        List<Image> images = new ArrayList<>();
        TextImage borderHorizontalImage = new TextImage(new Size(size.getWidth(), 5), new Appearance(' ', "#000000", "#484848"));
        TextImage borderVerticalImage = new TextImage(new Size(10, size.getHeight()), new Appearance(' ', "#000000", "#484848"));
        TextImage titleImage = new LoaderTextImageBuilder("paused").createTextImage();
        images.add(new Image(new Position(0, 0), borderHorizontalImage));
        images.add(new Image(new Position(0, size.getHeight() - 5), borderHorizontalImage));
        images.add(new Image(new Position(0, 0), borderVerticalImage));
        images.add(new Image(new Position(size.getWidth() - 10, 0), borderVerticalImage));
        images.add(new Image(new Position(size.getWidth() / 2 - titleImage.getSize().getWidth() / 2, size.getHeight() / 2 - titleImage.getSize().getHeight() / 2 - 8), titleImage));
        return images;
    }
}
