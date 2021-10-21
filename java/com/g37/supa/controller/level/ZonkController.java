package com.g37.supa.controller.level;

import com.g37.supa.Game;
import com.g37.supa.model.Position;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Zonk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ZonkController extends GravityController<Zonk> {
    public ZonkController(Level level) {
        super(level);
    }

    @Override
    public void step(Game game, GUI.KEYACTION action, long time) throws IOException {
        if (time - lastMovement > 500) {
            Map<Zonk, Position> zonks = new HashMap<>();
            for (Zonk zonk : getModel().getZonks())
                zonks.put(zonk, zonk.getPosition());
            moveElements(zonks);
            this.lastMovement = time;
        }
    }
}
