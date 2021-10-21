package com.g37.supa.model.level;

import com.g37.supa.model.Size;
import com.g37.supa.model.level.elements.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class LevelBuilder {
    public Level createLevel() throws IOException {
        Level level = new Level(getSize(), getElementSize(), getGravity());
        level.setInfotrons(createInfotrons());
        level.setWalls(createWalls());
        level.setBases(createBases());
        level.setZonks(createZonks());
        level.setXEnemies(createXEnemies());
        level.setEndBlocks(createEndBlocks());
        level.setJokers(createJokers());
        level.setMurphy(createMurphy());
        level.setPorts(createPorts());
        level.setExplosions(new ArrayList<>());
        return level;
    }

    protected abstract Size getSize();

    protected abstract Size getElementSize();

    protected abstract boolean getGravity();

    protected abstract List<Wall> createWalls() throws IOException;

    protected abstract List<Infotron> createInfotrons() throws IOException;

    protected abstract List<Base> createBases() throws IOException;

    protected abstract List<Zonk> createZonks() throws IOException;

    protected abstract List<XEnemy> createXEnemies() throws IOException;

    protected abstract List<EndBlock> createEndBlocks() throws IOException;

    protected abstract List<Joker> createJokers() throws IOException;

    protected abstract List<Port> createPorts() throws IOException;

    protected abstract Murphy createMurphy() throws IOException;
}
