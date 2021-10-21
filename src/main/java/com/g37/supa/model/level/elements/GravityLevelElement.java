package com.g37.supa.model.level.elements;

import com.g37.supa.model.Position;
import com.g37.supa.model.TextImage;

abstract public class GravityLevelElement extends LevelElement {
    private boolean falling;

    GravityLevelElement(Position position, TextImage textImage) {
        super(position, textImage);
        this.falling = false;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }
}
