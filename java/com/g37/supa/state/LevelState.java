package com.g37.supa.state;

import com.g37.supa.controller.Controller;
import com.g37.supa.controller.level.LevelController;
import com.g37.supa.model.level.Level;
import com.g37.supa.viewer.Viewer;
import com.g37.supa.viewer.level.LevelViewer;

public class LevelState extends State<Level> {

    public LevelState(Level model) {
        super(model);
    }

    @Override
    protected Viewer<Level> getViewer() {
        return new LevelViewer(getModel());
    }

    @Override
    protected Controller<Level> getController() {
        return new LevelController(getModel());
    }
}

