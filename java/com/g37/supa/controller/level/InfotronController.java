package com.g37.supa.controller.level;

import com.g37.supa.Game;
import com.g37.supa.model.Position;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Infotron;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InfotronController extends GravityController<Infotron> {
    public InfotronController(Level level) {
        super(level);
    }

    @Override
    public void step(Game game, GUI.KEYACTION action, long time) throws IOException {
        if (time - lastMovement > 500) {
            Map<Infotron, Position> infotrons = new HashMap<>();
            for (Infotron infotron : getModel().getInfotrons())
                infotrons.put(infotron, infotron.getPosition());
            moveElements(infotrons);
            this.lastMovement = time;
        }
    }
}