package com.g37.supa.model.level.elements;

import com.g37.supa.model.Position;
import com.g37.supa.model.TextImage;

public class Explosion extends LevelElement {
    private EXPLOSIONSTATE state;

    public Explosion(Position position, TextImage textImage, EXPLOSIONSTATE state) {
        super(position, textImage);
        this.state = state;
    }

    public EXPLOSIONSTATE getState() {
        return state;
    }

    public void setState(EXPLOSIONSTATE state) {
        this.state = state;
    }

    public enum EXPLOSIONSTATE {
        INITIAL,
        LAST
    }
}
