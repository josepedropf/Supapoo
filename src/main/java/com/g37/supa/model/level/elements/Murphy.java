package com.g37.supa.model.level.elements;

import com.g37.supa.model.Position;
import com.g37.supa.model.TextImage;

public class Murphy extends LevelElement {
    private int life;
    private boolean endIntention;

    public Murphy(Position position, TextImage textImage) {
        super(position, textImage);
        this.life = 1;
        this.endIntention = false;
    }

    public Murphy(Position position, TextImage textImage, int life) {
        super(position, textImage);
        this.life = life;
        this.endIntention = false;
    }

    public int getLife() {
        return this.life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean getEndIntention() {
        return this.endIntention;
    }

    public void setEndIntention(boolean endIntention) {
        this.endIntention = endIntention;
    }

    public void decreaseLife() {
        this.life--;
    }

    public void increaseLife() {
        this.life++;
    }
}