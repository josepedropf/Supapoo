package com.g37.supa.model.menu.elements;

import com.g37.supa.model.Position;

import java.util.Objects;

abstract public class MenuElement {
    private Position position;

    public MenuElement(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuElement menuElement = (MenuElement) o;
        return Objects.equals(position, menuElement.position);
    }
}
