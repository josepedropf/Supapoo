package com.g37.supa.state;

import com.g37.supa.controller.Controller;
import com.g37.supa.controller.menu.SplashMenuController;
import com.g37.supa.model.menu.Menu;
import com.g37.supa.viewer.Viewer;
import com.g37.supa.viewer.menu.MenuViewer;

public class SplashMenuState extends State<Menu> {

    public SplashMenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new SplashMenuController(getModel(), 2000);
    }
}
