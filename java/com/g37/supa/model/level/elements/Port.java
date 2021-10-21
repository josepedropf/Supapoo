package com.g37.supa.model.level.elements;

import com.g37.supa.model.Position;
import com.g37.supa.model.TextImage;

import java.util.List;

public class Port extends LevelElement {
    private List<Direction> waysIn;

    public Port(Position position, TextImage textImage) {
        super(position, textImage);
    }

    public Port(Position position, TextImage textImage, List<Direction> waysIn) {
        super(position, textImage);
        this.waysIn = waysIn;
    }

    public List<Direction> getWaysIn() {
        return this.waysIn;
    }

    public void setWaysIn(List<Direction> waysIn) {
        this.waysIn = waysIn;
    }

    public void addUp() {
        if (!inUp()) this.waysIn.add(Direction.UP);
    }

    public void addDown() {
        if (!inDown()) this.waysIn.add(Direction.DOWN);
    }

    public void addRight() {
        if (!inRight()) this.waysIn.add(Direction.RIGHT);
    }

    public void addLeft() {
        if (!inLeft()) this.waysIn.add(Direction.LEFT);
    }

    public boolean inUp() {
        for (Direction direction : waysIn) {
            if (direction == Direction.UP) return true;
        }
        return false;
    }

    public boolean inDown() {
        for (Direction direction : waysIn) {
            if (direction == Direction.DOWN) return true;
        }
        return false;
    }

    public boolean inRight() {
        for (Direction direction : waysIn) {
            if (direction == Direction.RIGHT) return true;
        }
        return false;
    }

    public boolean inLeft() {
        for (Direction direction : waysIn) {
            if (direction == Direction.LEFT) return true;
        }
        return false;
    }

    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }
}
