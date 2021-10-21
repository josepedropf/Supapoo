package com.g37.supa.controller.level;

import com.g37.supa.Game;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Explosion;
import com.g37.supa.model.level.elements.builders.ExplosionBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExplosionController extends LevelElementsController {
    public ExplosionController(Level level) {
        super(level);
    }

    @Override
    public void step(Game game, GUI.KEYACTION action, long time) throws IOException {
        if (lastMovement == 0) {
            lastMovement = time;
            explode();
        }
        if (time - lastMovement > 500) {
            explode();
            expand();
            explode();
            lastMovement = time;
        }
    }

    public void explode() {
        for (Explosion explosion : getModel().getExplosions()) {
            if (getModel().isMurphy(explosion.getPosition()))
                getModel().getMurphy().decreaseLife();
        }
    }

    public void expand() throws IOException {
        List<Explosion> add_explosions = new ArrayList<>();
        List<Explosion> remove_explosions = new ArrayList<>();
        for (Explosion explosion : getModel().getExplosions()) {
            if (explosion.getState() == Explosion.EXPLOSIONSTATE.INITIAL) {
                ExplosionBuilder explosionBuilder = new ExplosionBuilder(getModel().getElementSize(), Explosion.EXPLOSIONSTATE.LAST);
                remove_explosions.add(explosion);
                add_explosions.add(explosionBuilder.createElement(explosion.getPosition().getUp()));
                add_explosions.add(explosionBuilder.createElement(explosion.getPosition().getDown()));
                add_explosions.add(explosionBuilder.createElement(explosion.getPosition().getLeft()));
                add_explosions.add(explosionBuilder.createElement(explosion.getPosition().getRight()));
                add_explosions.add(explosionBuilder.createElement(explosion.getPosition().getUp().getLeft()));
                add_explosions.add(explosionBuilder.createElement(explosion.getPosition().getUp().getRight()));
                add_explosions.add(explosionBuilder.createElement(explosion.getPosition().getDown().getLeft()));
                add_explosions.add(explosionBuilder.createElement(explosion.getPosition().getDown().getRight()));
            } else if (explosion.getState() == Explosion.EXPLOSIONSTATE.LAST) {
                remove_explosions.add(explosion);
            }
        }
        for (Explosion explosion : add_explosions) {
            getModel().getExplosions().add(explosion);
        }
        for (Explosion explosion : remove_explosions) {
            getModel().getExplosions().remove(explosion);
        }
    }
}
