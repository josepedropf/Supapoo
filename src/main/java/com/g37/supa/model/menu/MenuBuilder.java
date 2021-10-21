package com.g37.supa.model.menu;

import com.g37.supa.model.Size;
import com.g37.supa.model.menu.elements.Button;
import com.g37.supa.model.menu.elements.Image;
import com.g37.supa.model.menu.elements.Title;

import java.io.IOException;
import java.util.List;

abstract public class MenuBuilder {
    protected final Size size;

    public MenuBuilder(Size size) {
        this.size = size;
    }

    public Menu createMenu() throws IOException {
        Menu menu = new Menu();
        menu.setTitles(createTitles());
        menu.setButtons(createButtons());
        menu.setImages(createImages());
        return menu;
    }

    protected abstract List<Title> createTitles();

    // Size of buttons should have an odd height > 1 and a width equal to the length of the text plus an even number > 0
    protected abstract List<Button> createButtons();

    protected abstract List<Image> createImages() throws IOException;
}
