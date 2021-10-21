package com.g37.supa.controller.level;

import com.g37.supa.Game;
import com.g37.supa.model.Position;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Joker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class JokerController extends LevelElementsController {
    public JokerController(Level level) {
        super(level);
    }

    @Override
    public void step(Game game, GUI.KEYACTION action, long time) throws IOException {
        if (time - lastMovement > 300) {
            moveJokers();
            lastMovement = time;
        }
    }

    public void moveJokers() {
        for (Joker joker : getModel().getJokers()) {
            ArrayList<Position> positions = new ArrayList<>();
            if (getModel().isEmpty(joker.getPosition().getLeft()) || getModel().isMurphy(joker.getPosition().getLeft()))
                positions.add(joker.getPosition().getLeft());
            if (getModel().isEmpty(joker.getPosition().getRight()) || getModel().isMurphy(joker.getPosition().getRight()))
                positions.add(joker.getPosition().getRight());
            if (getModel().isEmpty(joker.getPosition().getUp()) || getModel().isMurphy(joker.getPosition().getUp()))
                positions.add(joker.getPosition().getUp());
            if (getModel().isEmpty(joker.getPosition().getDown()) || getModel().isMurphy(joker.getPosition().getDown()))
                positions.add(joker.getPosition().getDown());

            if (positions.size() > 0) {
                joker.setPosition(positions.get(new Random().nextInt(positions.size())));
            }

            if (getModel().isMurphy(joker.getPosition())) getModel().getMurphy().decreaseLife();
        }
    }


}