package com.g37.supa.controller.menu;

import com.g37.supa.Game;
import com.g37.supa.controller.Controller;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.menu.Menu;

import java.io.IOException;

public class SplashMenuController extends Controller<Menu> {
    private final long displayTime;
    private long startTime;

    public SplashMenuController(Menu menu, long displayTime) {
        super(menu);
        this.displayTime = displayTime;
        this.startTime = 0;
    }

    @Override
    public void step(Game game, GUI.KEYACTION action, long time) throws IOException {
        if (startTime == 0) {
            startTime = time;
        }
        if (time - startTime > displayTime) {
            game.popState();
        }
    }

    public long getStartTime() {
        return this.startTime;
    }

}
