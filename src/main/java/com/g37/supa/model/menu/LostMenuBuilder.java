package com.g37.supa.model.menu;

import com.g37.supa.model.*;
import com.g37.supa.model.menu.elements.Button;
import com.g37.supa.model.menu.elements.Image;
import com.g37.supa.model.menu.elements.Title;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LostMenuBuilder extends MenuBuilder {
    public LostMenuBuilder(Size size) {
        super(size);
    }

    @Override
    protected List<Title> createTitles() {
        //List<Title> titles = new ArrayList<>();
        //Title title = new Title(new Position(size.getWidth() / 2 - 7, size.getHeight() / 2), new Text("YOU LOST... :(", "#000000", "#ff2222", EnumSet.of(Text.TEXTMODIFIER.BOLD)));
        //titles.add(title);
        return new ArrayList<>();
    }

    @Override
    protected List<Button> createButtons() {
        return new ArrayList<>();
    }

    @Override
    protected List<Image> createImages() throws IOException {
        List<Image> images = new ArrayList<>();
        TextImage borderHorizontalImage = new TextImage(new Size(size.getWidth(), 5), new Appearance(' ', "#000000", "#484848"));
        TextImage borderVerticalImage = new TextImage(new Size(10, size.getHeight()), new Appearance(' ', "#000000", "#484848"));
        TextImage titleImage = new LoaderTextImageBuilder("lost").createTextImage();
        images.add(new Image(new Position(0, 0), borderHorizontalImage));
        images.add(new Image(new Position(0, size.getHeight() - 5), borderHorizontalImage));
        images.add(new Image(new Position(0, 0), borderVerticalImage));
        images.add(new Image(new Position(size.getWidth() - 10, 0), borderVerticalImage));
        images.add(new Image(new Position(size.getWidth() / 2 - titleImage.getSize().getWidth() / 2, size.getHeight() / 2 - titleImage.getSize().getHeight() / 2), titleImage));
        return images;
    }
}
