package com.g37.supa.controller.level;

import com.g37.supa.Game;
import com.g37.supa.model.Position;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Port;
import com.g37.supa.model.level.elements.Zonk;

public class MurphyController extends LevelElementsController {
    public MurphyController(Level level) {
        super(level);
    }

    public int checkPort(Position position, Port.Direction direction) {
        Port port;
        switch (direction) {
            case RIGHT:
                port = getModel().getPort(position.getRight());
                if (port != null) {
                    if (port.inLeft()) {
                        return (1 + checkPort(position.getRight(), Port.Direction.RIGHT));
                    }
                }
                break;
            case LEFT:
                port = getModel().getPort(position.getLeft());
                if (port != null) {
                    if (port.inRight()) {
                        return (1 + checkPort(position.getLeft(), Port.Direction.LEFT));
                    }
                }
                break;
            case UP:
                port = getModel().getPort(position.getUp());
                if (port != null) {
                    if (port.inDown()) {
                        return (1 + checkPort(position.getUp(), Port.Direction.UP));
                    }
                }
                break;
            case DOWN:
                port = getModel().getPort(position.getDown());
                if (port != null) {
                    if (port.inUp()) {
                        return (1 + checkPort(position.getDown(), Port.Direction.DOWN));
                    }
                }
                break;
            default:
                return 0;
        }
        return 0;
    }

    public void moveMurphyLeft() {
        int nports = checkPort(getModel().getMurphy().getPosition(), Port.Direction.LEFT);
        if (nports > 0) {
            Position position = getModel().getMurphy().getPosition().getLeft();
            for (int i = 0; i < nports; i++) {
                position = position.getLeft();
            }
            moveMurphy(position);
        } else {
            Zonk leftZonk = getModel().getZonk(getModel().getMurphy().getPosition().getLeft());
            if (leftZonk != null && getModel().isEmpty(getModel().getMurphy().getPosition().getLeft().getLeft()) && !getModel().isEmpty(getModel().getMurphy().getPosition().getLeft().getDown())) {
                leftZonk.setPosition(leftZonk.getPosition().getLeft());
            }
            moveMurphy(getModel().getMurphy().getPosition().getLeft());
        }
    }

    public void moveMurphyRight() {
        int nports = checkPort(getModel().getMurphy().getPosition(), Port.Direction.RIGHT);
        if (nports > 0) {
            Position position = getModel().getMurphy().getPosition().getRight();
            for (int i = 0; i < nports; i++) {
                position = position.getRight();
            }
            moveMurphy(position);
        } else {
            Zonk rightZonk = getModel().getZonk(getModel().getMurphy().getPosition().getRight());
            if (rightZonk != null && getModel().isEmpty(getModel().getMurphy().getPosition().getRight().getRight()) && !getModel().isEmpty(getModel().getMurphy().getPosition().getRight().getDown())) {
                rightZonk.setPosition(rightZonk.getPosition().getRight());
            }
            moveMurphy(getModel().getMurphy().getPosition().getRight());
        }
    }

    public void moveMurphyUp() {
        int nports = checkPort(getModel().getMurphy().getPosition(), Port.Direction.UP);
        if (nports > 0) {
            Position position = getModel().getMurphy().getPosition().getUp();
            for (int i = 0; i < nports; i++) {
                position = position.getUp();
            }
            moveMurphy(position);
        } else moveMurphy(getModel().getMurphy().getPosition().getUp());
    }

    public void moveMurphyDown() {
        int nports = checkPort(getModel().getMurphy().getPosition(), Port.Direction.DOWN);
        if (nports > 0) {
            Position position = getModel().getMurphy().getPosition().getDown();
            for (int i = 0; i < nports; i++) {
                position = position.getDown();
            }
            moveMurphy(position);
        } else moveMurphy(getModel().getMurphy().getPosition().getDown());
    }

    public void slurpMurphyDown() {
        slurpMurphy(getModel().getMurphy().getPosition().getDown());
    }

    public void slurpMurphyRight() {
        slurpMurphy(getModel().getMurphy().getPosition().getRight());
    }

    public void slurpMurphyLeft() {
        slurpMurphy(getModel().getMurphy().getPosition().getLeft());
    }

    public void slurpMurphyUp() {
        slurpMurphy(getModel().getMurphy().getPosition().getUp());
    }

    public void decreaseMurphyLife() {
        getModel().getMurphy().decreaseLife();
    }

    public void increaseMurphyLife() {
        getModel().getMurphy().increaseLife();
    }

    private void moveMurphy(Position position) {
        if (!getModel().isSolid(position)) {
            getModel().getMurphy().setPosition(position);
            if (getModel().isInfotron(position)) getModel().removeInfotron(position);
            if (getModel().isBase(position)) getModel().removeBase(position);
            if (getModel().isXEnemy(position) || getModel().isJoker(position)) {
                getModel().getMurphy().decreaseLife();
            }
        } else if (getModel().isEndBlock(position)) getModel().getMurphy().setEndIntention(true);
    }

    private void slurpMurphy(Position position) {
        if (getModel().isBase(position)) getModel().removeBase(position);
        if (getModel().isInfotron(position)) getModel().removeInfotron(position);
    }

    @Override
    public void step(Game game, GUI.KEYACTION action, long time) {
        if (getModel().getMurphy().getLife() == 0) return;
        switch (action) {
            case UP:
                moveMurphyUp();
                break;
            case RIGHT:
                moveMurphyRight();
                break;
            case DOWN:
                moveMurphyDown();
                break;
            case LEFT:
                moveMurphyLeft();
                break;
            case SLURP_UP:
                slurpMurphyUp();
                break;
            case SLURP_RIGHT:
                slurpMurphyRight();
                break;
            case SLURP_DOWN:
                slurpMurphyDown();
                break;
            case SLURP_LEFT:
                slurpMurphyLeft();
                break;
        }
    }
}
