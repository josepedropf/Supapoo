package com.g37.supa.model.menu;

import com.g37.supa.model.*;
import com.g37.supa.model.menu.elements.Button;
import com.g37.supa.model.menu.elements.Image;
import com.g37.supa.model.menu.elements.Title;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class LevelMenuBuilder extends MenuBuilder {
    public LevelMenuBuilder(Size size) {
        super(size);
    }

    @Override
    protected List<Title> createTitles() {
        //List<Title> titles = new ArrayList<>();
        //Title title = new Title(new Position(size.getWidth() / 2 - 6, size.getHeight() / 2 - 8), new Text("SELECT LEVEL", "#000000", "#ff2222", EnumSet.of(Text.TEXTMODIFIER.BOLD)));
        //titles.add(title);
        return new ArrayList<>();
    }

    @Override
    protected List<Button> createButtons() {
        List<Button> buttons = new ArrayList<>();
        String color = "#ffbb00";
        String colorHigh = "#0088ff";
        String background = "#000000";
        Text plusText = new Text("+", color, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text plusHighText = new Text("+", colorHigh, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text levelText = new Text("01", color, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text levelHighText = new Text("01", colorHigh, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text minusText = new Text("-", color, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text minusHighText = new Text("-", colorHigh, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text backText = new Text("Back", color, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text backHighText = new Text("Back", colorHigh, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        buttons.add(new Button(new Position(size.getWidth() / 2 - 3, size.getHeight() / 2 + 1), plusText, plusHighText, new Size(6, 1), Button.BUTTONACTION.PLUS));
        buttons.add(new Button(new Position(size.getWidth() / 2 - 3, size.getHeight() / 2 + 3), levelText, levelHighText, new Size(6, 3), Button.BUTTONACTION.LVL_SELECT));
        buttons.add(new Button(new Position(size.getWidth() / 2 - 3, size.getHeight() / 2 + 7), minusText, minusHighText, new Size(6, 1), Button.BUTTONACTION.MINUS));
        buttons.add(new Button(new Position(size.getWidth() / 2 - 5, size.getHeight() / 2 + 10), backText, backHighText, new Size(10, 3), Button.BUTTONACTION.CONTINUE));
        return buttons;
    }

    @Override
    protected List<Image> createImages() throws IOException {
        List<Image> images = new ArrayList<>();
        TextImage borderHorizontalImage = new TextImage(new Size(size.getWidth(), 5), new Appearance(' ', "#000000", "#484848"));
        TextImage borderVerticalImage = new TextImage(new Size(10, size.getHeight()), new Appearance(' ', "#000000", "#484848"));
        TextImage titleImage = new LoaderTextImageBuilder("select").createTextImage();
        images.add(new Image(new Position(0, 0), borderHorizontalImage));
        images.add(new Image(new Position(0, size.getHeight() - 5), borderHorizontalImage));
        images.add(new Image(new Position(0, 0), borderVerticalImage));
        images.add(new Image(new Position(size.getWidth() - 10, 0), borderVerticalImage));
        images.add(new Image(new Position(size.getWidth() / 2 - titleImage.getSize().getWidth() / 2, size.getHeight() / 2 - titleImage.getSize().getHeight() - 2), titleImage));
        return images;
    }
}
