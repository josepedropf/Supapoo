package com.g37.supa.controller.level;

import com.g37.supa.model.Position;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Explosion;
import com.g37.supa.model.level.elements.GravityLevelElement;
import com.g37.supa.model.level.elements.Infotron;
import com.g37.supa.model.level.elements.Zonk;
import com.g37.supa.model.level.elements.builders.ExplosionBuilder;

import java.io.IOException;
import java.util.Map;

public abstract class GravityController<T extends GravityLevelElement> extends LevelElementsController {
    public GravityController(Level level) {
        super(level);
    }

    public void moveElements(Map<T, Position> elements) throws IOException {
        // Element down
        for (T element : elements.keySet()) {
            if (getModel().isEmpty(element.getPosition().getDown()))
                elements.replace(element, element.getPosition().getDown());
            else if (element.isFalling()) {
                //if (getModel().isMurphy(element.getPosition().getDown()) || getModel().isInfotron(element.getPosition().getDown())) {
                if (getModel().isMurphy(element.getPosition().getDown())) {
                    getModel().getExplosions().add(new ExplosionBuilder(getModel().getElementSize(), Explosion.EXPLOSIONSTATE.INITIAL).createElement(element.getPosition().getDown()));
                    Infotron infotron = getModel().getInfotron(element.getPosition());
                    if (infotron != null) getModel().getInfotrons().remove(infotron);
                    Zonk zonk = getModel().getZonk(element.getPosition());
                    if (zonk != null) getModel().getZonks().remove(zonk);
                }
                element.setFalling(false);
            }
        }
        // Element left
        for (T element : elements.keySet()) {
            if (element.getPosition().equals(elements.get(element)) && (getModel().isInfotron(element.getPosition().getDown()) || getModel().isZonk(element.getPosition().getDown())) && !getModel().isInfotron(element.getPosition().getUp()) && !getModel().isZonk(element.getPosition().getUp())) {
                if (!elements.containsValue(element.getPosition().getDown().getLeft()) && getModel().isEmpty(element.getPosition().getLeft()) && getModel().isEmpty(element.getPosition().getDown().getLeft())) {
                    elements.replace(element, element.getPosition().getDown().getLeft());
                }
            }
        }
        // Element right
        for (T element : elements.keySet()) {
            if (element.getPosition().equals(elements.get(element)) && (getModel().isInfotron(element.getPosition().getDown()) || getModel().isZonk(element.getPosition().getDown())) && !getModel().isInfotron(element.getPosition().getUp()) && !getModel().isZonk(element.getPosition().getUp())) {
                if (!elements.containsValue(element.getPosition().getDown().getRight()) && getModel().isEmpty(element.getPosition().getRight()) && getModel().isEmpty(element.getPosition().getDown().getRight())) {
                    elements.replace(element, element.getPosition().getDown().getRight());
                }
            }
        }
        for (T element : elements.keySet()) {
            if (!element.getPosition().equals(elements.get(element))) element.setFalling(true);
            element.setPosition(elements.get(element));
        }
    }
}

