package com.g37.supa.model.menu.elements;

import com.g37.supa.model.Position;
import com.g37.supa.model.TextImage;

public class Image extends MenuElement {
    private TextImage textImage;

    public Image(Position position, TextImage textImage) {
        super(position);
        this.textImage = textImage;
    }

    public TextImage getTextImage() {
        return textImage;
    }

    public void setTextImage(TextImage textImage) {
        this.textImage = textImage;
    }
}
