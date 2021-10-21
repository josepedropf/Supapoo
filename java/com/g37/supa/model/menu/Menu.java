package com.g37.supa.model.menu;

import com.g37.supa.model.menu.elements.Button;
import com.g37.supa.model.menu.elements.Image;
import com.g37.supa.model.menu.elements.Title;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Title> titles;
    private List<Button> buttons;
    private List<Image> images;
    private int selectedButton;

    public Menu() {
        this.selectedButton = 0;
        this.titles = new ArrayList<>();
        this.buttons = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        for (Button button : buttons) {
            button.setHover(false);
        }
        if (!buttons.isEmpty())
            buttons.get(selectedButton).setHover(true);
        this.buttons = buttons;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void upButton() {
        buttons.get(selectedButton).setHover(false);
        if (selectedButton == 0)
            selectedButton = getButtons().size() - 1;
        else
            selectedButton--;
        buttons.get(selectedButton).setHover(true);
    }

    public void downButton() {
        buttons.get(selectedButton).setHover(false);
        if (selectedButton == getButtons().size() - 1)
            selectedButton = 0;
        else
            selectedButton++;
        buttons.get(selectedButton).setHover(true);
    }

    public Button getSelectedButton() {
        if (buttons.size() == 0) return null;
        return buttons.get(this.selectedButton);
    }

    public int getSelectedButtonNumber() {
        return this.selectedButton;
    }

    public void setSelectedButton(int button_no) {
        if (buttons.size() == 0) selectedButton = 0;
        else {
            if (button_no < 0) button_no = 0;
            if (button_no > buttons.size() - 1) button_no = buttons.size() - 1;
            selectedButton = button_no;
        }
    }
}
