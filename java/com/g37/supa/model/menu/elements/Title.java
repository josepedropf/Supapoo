package com.g37.supa.model.menu.elements;

import com.g37.supa.model.Position;
import com.g37.supa.model.Text;

public class Title extends MenuElement {
    private Text text;

    public Title(Position position, Text text) {
        super(position);
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
