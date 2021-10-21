package com.g37.supa.model.level.elements;

import com.g37.supa.model.Position;
import com.g37.supa.model.TextImage;

import java.util.Objects;

abstract public class LevelElement {
    private Position position;
    private final TextImage textImage;

    public LevelElement(Position position, TextImage textImage) {
        this.position = position;
        this.textImage = textImage;
    }

    public TextImage getTextImage() {
        return this.textImage;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelElement element = (LevelElement) o;
        return Objects.equals(position, element.position);
    }

}