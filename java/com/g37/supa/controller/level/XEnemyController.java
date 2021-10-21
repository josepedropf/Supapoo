package com.g37.supa.controller.level;

import com.g37.supa.Game;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.XEnemy;

import java.io.IOException;

public class XEnemyController extends LevelElementsController {
    public XEnemyController(Level level) {
        super(level);
    }

    @Override
    public void step(Game game, GUI.KEYACTION action, long time) throws IOException {
        if (time - lastMovement > 300) {
            moveXEnemies();
            lastMovement = time;
        }
    }

    public void moveXEnemies() {
        for (XEnemy xenemy : getModel().getXEnemies()) {
            switch (xenemy.getMoveState()) {
                case LEFT:
                    if (getModel().isEmpty(xenemy.getPosition().getLeft()) || getModel().isMurphy(xenemy.getPosition().getLeft()))
                        xenemy.setPosition(xenemy.getPosition().getLeft());
                    else {
                        xenemy.setMoveState(XEnemy.Direction.RIGHT);
                        if (getModel().isEmpty(xenemy.getPosition().getRight()) || getModel().isMurphy(xenemy.getPosition().getRight()))
                            xenemy.setPosition(xenemy.getPosition().getRight());
                    }
                    break;

                case RIGHT:
                    if (getModel().isEmpty(xenemy.getPosition().getRight()) || getModel().isMurphy(xenemy.getPosition().getRight()))
                        xenemy.setPosition(xenemy.getPosition().getRight());
                    else {
                        xenemy.setMoveState(XEnemy.Direction.LEFT);
                        if (getModel().isEmpty(xenemy.getPosition().getLeft()) || getModel().isMurphy(xenemy.getPosition().getLeft()))
                            xenemy.setPosition(xenemy.getPosition().getLeft());
                    }
                    break;
            }
            if (getModel().isMurphy(xenemy.getPosition())) getModel().getMurphy().decreaseLife();
        }
    }


}
