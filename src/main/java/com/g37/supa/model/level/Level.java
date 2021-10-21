package com.g37.supa.model.level;

import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.level.elements.*;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final Size size;
    private final Size elementSize;
    private boolean gravity;
    private Murphy murphy;
    private List<Wall> walls;
    private List<Infotron> infotrons;
    private List<Base> bases;
    private List<Zonk> zonks;
    private List<XEnemy> xenemies;
    private List<EndBlock> endblocks;
    private List<Explosion> explosions;
    private List<Joker> jokers;
    private List<Port> ports;

    public Level(Size size, Size elementSize, boolean gravity) {
        this.size = size;
        this.elementSize = elementSize;
        this.gravity = gravity;
        this.walls = new ArrayList<>();
        this.infotrons = new ArrayList<>();
        this.bases = new ArrayList<>();
        this.zonks = new ArrayList<>();
        this.xenemies = new ArrayList<>();
        this.endblocks = new ArrayList<>();
        this.explosions = new ArrayList<>();
        this.jokers = new ArrayList<>();
        this.ports = new ArrayList<>();
    }

    public Size getSize() {
        return size;
    }

    public Size getElementSize() {
        return elementSize;
    }

    public Murphy getMurphy() {
        return murphy;
    }

    public void setMurphy(Murphy murphy) {
        this.murphy = murphy;
    }

    public List<Port> getPorts() {
        return this.ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public List<Joker> getJokers() {
        return jokers;
    }

    public void setJokers(List<Joker> jokers) {
        this.jokers = jokers;
    }

    public boolean getGravity() {
        return this.gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public List<Infotron> getInfotrons() {
        return infotrons;
    }

    public void setInfotrons(List<Infotron> infotrons) {
        this.infotrons = infotrons;
    }

    public List<Base> getBases() {
        return bases;
    }

    public void setBases(List<Base> bases) {
        this.bases = bases;
    }

    public List<XEnemy> getXEnemies() {
        return xenemies;
    }

    public void setXEnemies(List<XEnemy> xenemies) {
        this.xenemies = xenemies;
    }

    public List<Zonk> getZonks() {
        return zonks;
    }

    public void setZonks(List<Zonk> zonks) {
        this.zonks = zonks;
    }

    public List<EndBlock> getEndBlocks() {
        return endblocks;
    }

    public void setEndBlocks(List<EndBlock> endblocks) {
        this.endblocks = endblocks;
    }

    public List<Explosion> getExplosions() {
        return explosions;
    }

    public void setExplosions(List<Explosion> explosions) {
        this.explosions = explosions;
    }

    public boolean removeInfotron(Position position) {
        for (Infotron infotron : infotrons) {
            if (infotron.getPosition().equals(position)) {
                this.infotrons.remove(infotron);
                return true;
            }
        }
        return false;
    }

    public boolean removeBase(Position position) {
        for (Base base : bases) {
            if (base.getPosition().equals(position)) {
                this.bases.remove(base);
                return true;
            }
        }
        return false;
    }

    public boolean isMurphy(Position position) {
        return murphy.getPosition().equals(position);
    }

    public boolean isWall(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean isBase(Position position) {
        for (Base base : bases) {
            if (base.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean isExplosion(Position position) {
        for (Explosion explosion : explosions) {
            if (explosion.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean isInfotron(Position position) {
        for (Infotron infotron : infotrons) {
            if (infotron.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean isPort(Position position) {
        for (Port port : ports) {
            if (port.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean isXEnemy(Position position) {
        for (XEnemy xenemy : xenemies) {
            if (xenemy.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean isZonk(Position position) {
        for (Zonk zonk : zonks) {
            if (zonk.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean isJoker(Position position) {
        for (Joker joker : jokers) {
            if (joker.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean isEndBlock(Position position) {
        for (EndBlock endblock : endblocks) {
            if (endblock.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public Wall getWall(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position))
                return wall;
        }
        return null;
    }

    public Base getBase(Position position) {
        for (Base base : bases) {
            if (base.getPosition().equals(position))
                return base;
        }
        return null;
    }

    public Explosion getExplosion(Position position) {
        for (Explosion explosion : explosions) {
            if (explosion.getPosition().equals(position))
                return explosion;
        }
        return null;
    }

    public Infotron getInfotron(Position position) {
        for (Infotron infotron : infotrons) {
            if (infotron.getPosition().equals(position))
                return infotron;
        }
        return null;
    }

    public XEnemy getXEnemy(Position position) {
        for (XEnemy xenemy : xenemies) {
            if (xenemy.getPosition().equals(position))
                return xenemy;
        }
        return null;
    }

    public Zonk getZonk(Position position) {
        for (Zonk zonk : zonks) {
            if (zonk.getPosition().equals(position))
                return zonk;
        }
        return null;
    }

    public Joker getJoker(Position position) {
        for (Joker joker : jokers) {
            if (joker.getPosition().equals(position))
                return joker;
        }
        return null;
    }

    public Port getPort(Position position) {
        for (Port port : ports) {
            if (port.getPosition().equals(position))
                return port;
        }
        return null;
    }

    public EndBlock getEndBlock(Position position) {
        for (EndBlock endblock : endblocks) {
            if (endblock.getPosition().equals(position))
                return endblock;
        }
        return null;
    }

    public boolean isEmpty(Position position) {
        return !isMurphy(position) && !isPort(position) && !isXEnemy(position) && !isJoker(position) && !isWall(position) && !isBase(position) && !isInfotron(position) && !isZonk(position) && !isEndBlock(position);
    }

    public boolean isSolid(Position position) {
        return isWall(position) || isZonk(position) || isEndBlock(position) || isPort(position);
    }
}
