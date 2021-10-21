package com.g37.supa.state;

import com.g37.supa.controller.Controller;
import com.g37.supa.controller.menu.MenuController;
import com.g37.supa.model.menu.Menu;
import com.g37.supa.viewer.Viewer;
import com.g37.supa.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {

    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}
