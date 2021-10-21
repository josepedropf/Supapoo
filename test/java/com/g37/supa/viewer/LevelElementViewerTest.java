package com.g37.supa.viewer;

import com.g37.supa.model.Appearance;
import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.gui.GUI;
import com.g37.supa.model.level.elements.Murphy;
import com.g37.supa.viewer.level.LevelElementViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class LevelElementViewerTest {
    private Murphy murphy;
    private LevelElementViewer viewer;
    private GUI gui;
    private TextImage textImage;

    @BeforeEach
    void setUp() throws IOException {
        textImage = new LoaderTextImageBuilder("test").createTextImage();
        murphy = new Murphy(new Position(10, 10), textImage);
        viewer = new LevelElementViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        Position position = new Position(2, 2);
        viewer.drawElement(murphy, gui, position);
        Mockito.verify(gui, Mockito.times(1)).drawTextImage(position, murphy.getTextImage());
    }
}