package com.g37.supa.model.menu.elements;

import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.Text;

public class Button extends MenuElement {
    private final BUTTONACTION action;
    private boolean hover;
    private Text text;
    private Text hoverText;
    private Size size;

    public Button(Position position, Text text, Text hoverText, Size size, BUTTONACTION action) {
        super(position);
        this.hover = false;
        this.text = text;
        this.hoverText = hoverText;
        this.size = size;
        this.action = action;
    }

    public Button(Position position, Text text, Size size, BUTTONACTION action) {
        super(position);
        this.hover = false;
        this.text = text;
        this.hoverText = text;
        this.size = size;
        this.action = action;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Text getHoverText() {
        return hoverText;
    }

    public void setHoverText(Text hoverText) {
        this.hoverText = hoverText;
    }

    public boolean getHover() {
        return this.hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public BUTTONACTION getAction() {
        return action;
    }

    public enum BUTTONACTION {
        QUIT,
        CONTINUE,
        START,
        LVL_SELECT,
        SELECT_LVL,
        PLUS,
        MINUS,
        MAIN,
        NONE
    }
}
