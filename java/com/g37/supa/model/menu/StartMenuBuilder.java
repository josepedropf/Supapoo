package com.g37.supa.model.menu;

import com.g37.supa.model.*;
import com.g37.supa.model.menu.elements.Button;
import com.g37.supa.model.menu.elements.Image;
import com.g37.supa.model.menu.elements.Title;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class StartMenuBuilder extends MenuBuilder {
    public StartMenuBuilder(Size size) {
        super(size);
    }

    @Override
    protected List<Title> createTitles() {
        //List<Title> titles = new ArrayList<>();
        //Title title = new Title(new Position(size.getWidth() / 2 - 3, size.getHeight() / 8), new Text("SUPAPOO", "#000000", "#ff2222", EnumSet.of(Text.TEXTMODIFIERS.BOLD)));
        //titles.add(title);
        return new ArrayList<>();
    }

    @Override
    protected List<Button> createButtons() {
        List<Button> buttons = new ArrayList<>();
        String color = "#ffbb00";
        String colorHigh = "#0088ff";
        String background = "#000000";
        Text startText = new Text("START", color, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text startHighText = new Text("START", colorHigh, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text levelText = new Text("SELECT LEVEL", color, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text levelHighText = new Text("SELECT LEVEL", colorHigh, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text quitText = new Text("QUIT", color, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        Text quitHighText = new Text("QUIT", colorHigh, background, EnumSet.of(Text.TEXTMODIFIER.BOLD));
        buttons.add(new Button(new Position(size.getWidth() / 2 - 8, size.getHeight() / 2 - 1), startText, startHighText, new Size(16, 3), Button.BUTTONACTION.START));
        buttons.add(new Button(new Position(size.getWidth() / 2 - 8, size.getHeight() / 2 + 3), levelText, levelHighText, new Size(16, 3), Button.BUTTONACTION.SELECT_LVL));
        buttons.add(new Button(new Position(size.getWidth() / 2 - 8, size.getHeight() / 2 + 7), quitText, quitHighText, new Size(16, 3), Button.BUTTONACTION.QUIT));
        return buttons;
    }

    @Override
    protected List<Image> createImages() throws IOException {
        List<Image> images = new ArrayList<>();
        TextImage titleImage = new LoaderTextImageBuilder("supa").createTextImage();
        TextImage zonkImage = new LoaderTextImageBuilder("zonk").createTextImage();
        TextImage infotronImage = new LoaderTextImageBuilder("infotron").createTextImage();
        TextImage borderHorizontalImage = new TextImage(new Size(size.getWidth(), 5), new Appearance(' ', "#000000", "#484848"));
        TextImage borderVerticalImage = new TextImage(new Size(10, size.getHeight()), new Appearance(' ', "#000000", "#484848"));
        images.add(new Image(new Position(0, 0), borderHorizontalImage));
        images.add(new Image(new Position(0, size.getHeight() - 5), borderHorizontalImage));
        images.add(new Image(new Position(0, 0), borderVerticalImage));
        images.add(new Image(new Position(size.getWidth() - 10, 0), borderVerticalImage));
        images.add(new Image(new Position(size.getWidth() - 20, size.getHeight() - 10), zonkImage));
        images.add(new Image(new Position(size.getWidth() - 20, size.getHeight() - 15), zonkImage));
        images.add(new Image(new Position(size.getWidth() - 20, size.getHeight() - 20), zonkImage));
        images.add(new Image(new Position(size.getWidth() - 30, size.getHeight() - 10), zonkImage));
        images.add(new Image(new Position(size.getWidth() - 30, size.getHeight() - 15), zonkImage));
        images.add(new Image(new Position(size.getWidth() - 40, size.getHeight() - 10), zonkImage));
        images.add(new Image(new Position(10, size.getHeight() - 10), infotronImage));
        images.add(new Image(new Position(10, size.getHeight() - 15), infotronImage));
        images.add(new Image(new Position(10, size.getHeight() - 20), infotronImage));
        images.add(new Image(new Position(20, size.getHeight() - 10), infotronImage));
        images.add(new Image(new Position(20, size.getHeight() - 15), infotronImage));
        images.add(new Image(new Position(30, size.getHeight() - 10), infotronImage));
        images.add(new Image(new Position(size.getWidth() / 2 - titleImage.getSize().getWidth() / 2, size.getHeight() / 2 - 11), titleImage));

        return images;
    }
}
