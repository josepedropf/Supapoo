package com.g37.supa.controller.level;

import com.g37.supa.Game;
import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.level.Level;
import com.g37.supa.model.level.elements.Murphy;

import java.io.IOException;

public class LevelController extends LevelElementsController {
    private final MurphyController murphyController;
    private final ZonkController zonkController;
    private final InfotronController infotronController;
    private final ExplosionController explosionController;
    private final XEnemyController xenemyController;
    private final JokerController jokerController;
    private long murphyDeath;

    public LevelController(Level level) {
        super(level);
        this.murphyController = new MurphyController(level);
        this.xenemyController = new XEnemyController(level);
        this.jokerController = new JokerController(level);
        this.zonkController = new ZonkController(level);
        this.infotronController = new InfotronController(level);
        this.explosionController = new ExplosionController(level);
        this.murphyDeath = -1;
    }

    @Override
    public void step(Game game, GUI.KEYACTION action, long time) throws IOException {
        if (getModel().getMurphy().getLife() == 0 && murphyDeath == -1) {
            getModel().setMurphy(new Murphy(getModel().getMurphy().getPosition(), new LoaderTextImageBuilder("deadmurphy").createTextImage(), 0));
            murphyDeath = time;
        }
        if (murphyDeath != -1 && time - murphyDeath > 700) {
            game.lost();
        } else if (action == GUI.KEYACTION.QUIT) {
            game.popState();
        } else if (action == GUI.KEYACTION.CLOSE) {
            game.pushState(null);
        } else if (action == GUI.KEYACTION.PAUSE) {
            game.pause();
        } else if (action == GUI.KEYACTION.SKIP) {
            game.skipLevel();
        } else if (action == GUI.KEYACTION.RESTART) {
            game.restartLevel();
        } else if (getModel().getMurphy().getEndIntention()) {
            if (getModel().getInfotrons().size() == 0) {
                game.nextLevel();
            } else getModel().getMurphy().setEndIntention(false);
        } else {
            murphyController.step(game, action, time);
            xenemyController.step(game, action, time);
            jokerController.step(game, action, time);
            explosionController.step(game, action, time);
            zonkController.step(game, action, time);
            infotronController.step(game, action, time);
        }
    }


}
