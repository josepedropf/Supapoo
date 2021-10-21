package com.g37.supa.controller.menu;

import com.g37.supa.Game;
import com.g37.supa.controller.Controller;
import com.g37.supa.model.Text;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.menu.Menu;
import com.g37.supa.model.menu.elements.Button;

import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.KEYACTION action, long time) throws IOException {
        switch (action) {
            case DOWN:
                getModel().downButton();
                break;
            case UP:
                getModel().upButton();
                break;
            case SELECT:
                //Change State
                switch (getModel().getSelectedButton().getAction()) {
                    case START:
                        game.start();
                        break;
                    case CONTINUE:
                        game.popState();
                        break;
                    case MAIN:
                        game.popState();
                        game.popState();
                        break;
                    case MINUS:
                        if (game.getCurrentLevel() > 0) {
                            game.decrementLevel();
                        }
                        for (Button button : getModel().getButtons()) {
                            if (button.getAction() == Button.BUTTONACTION.LVL_SELECT) {
                                button.setText(new Text(String.format("%02d", game.getCurrentLevel()), button.getText().getBackgroundColor(), button.getText().getForegroundColor(), button.getText().getModifiers()));
                                button.setHoverText(new Text(String.format("%02d", game.getCurrentLevel()), button.getHoverText().getBackgroundColor(), button.getHoverText().getForegroundColor(), button.getText().getModifiers()));
                            }
                        }
                        break;
                    case PLUS:
                        if (game.getCurrentLevel() < game.getTotalLevels()) {
                            game.incrementLevel();
                        }
                        for (Button button : getModel().getButtons()) {
                            if (button.getAction() == Button.BUTTONACTION.LVL_SELECT) {
                                button.setText(new Text(String.format("%02d", game.getCurrentLevel()), button.getText().getBackgroundColor(), button.getText().getForegroundColor(), button.getText().getModifiers()));
                                button.setHoverText(new Text(String.format("%02d", game.getCurrentLevel()), button.getHoverText().getBackgroundColor(), button.getHoverText().getForegroundColor(), button.getText().getModifiers()));
                            }
                        }
                        break;
                    case SELECT_LVL:
                        game.selectLevel();
                        break;
                    case LVL_SELECT:
                        game.popState();
                        game.play();
                        break;
                    case QUIT:
                        game.pushState(null);
                        break;
                    default:
                        break;
                }
                break;
            case CLOSE:
                game.pushState(null);
                break;
            default:
                break;
        }
    }

    /*
    public void run() throws IOException {
        for (Button button : menu.getButtons()) {
            button.setHover(false);
        }
        menu.getButtons().get(0).setHover(true);
        viewer.draw();
        int selectedButton = 0;
        while (menuStatus == MenuStatus.NONE) {
            GUI.KEYACTION action = gui.getNextAction();
            menu.getButtons().get(selectedButton).setHover(false);
            if (action == GUI.KEYACTION.DOWN) {
                if (selectedButton == menu.getButtons().size() - 1)
                    selectedButton = 0;
                else
                    selectedButton++;
            }
            if (action == GUI.KEYACTION.UP) {
                if (selectedButton == 0)
                    selectedButton = menu.getButtons().size() - 1;
                else
                    selectedButton--;
            }
            if (action == GUI.KEYACTION.UP || action == GUI.KEYACTION.DOWN) {
                menu.getButtons().get(selectedButton).setHover(true);
                viewer.draw();
            }
            if (action == GUI.KEYACTION.SELECT) {
                menuStatus = menu.getButtons().get(selectedButton).getAction();
            }
        }
    }
    */
}
