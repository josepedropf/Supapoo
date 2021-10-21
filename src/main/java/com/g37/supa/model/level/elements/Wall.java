package com.g37.supa.model.level.elements;

import com.g37.supa.model.Position;
import com.g37.supa.model.TextImage;

public class Wall extends LevelElement {
    private int damage;
    private boolean destructable;

    public Wall(Position position, TextImage textImage) {
        super(position, textImage);
        this.damage = 0;
        this.destructable = false;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isDestructable() {
        return destructable;
    }

    public void setDestructable(boolean destructable) {
        this.destructable = destructable;
    }
}