package com.g37.supa.controller.level;

import com.g37.supa.controller.Controller;
import com.g37.supa.model.level.Level;

public abstract class LevelElementsController extends Controller<Level> {
    protected long lastMovement;

    public LevelElementsController(Level level) {
        super(level);
        this.lastMovement = 0;
    }

    public void setLastMovement(long lastMovement) {
        this.lastMovement = lastMovement;
    }
}
