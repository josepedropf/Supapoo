package com.g37.supa.model.level.elements;

import com.g37.supa.model.Position;
import com.g37.supa.model.TextImage;


public class XEnemy extends LevelElement {
    private Direction moveState = Direction.RIGHT;

    public XEnemy(Position position, TextImage textImage) {
        super(position, textImage);
    }

    public Direction getMoveState() {
        return this.moveState;
    }

    public void setMoveState(Direction moveState) {
        this.moveState = moveState;
    }

    public enum Direction {
        RIGHT,
        LEFT
    }
}
