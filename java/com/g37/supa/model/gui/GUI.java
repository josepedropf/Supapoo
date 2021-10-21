package com.g37.supa.model.gui;

import com.g37.supa.model.*;

import java.io.IOException;

public interface GUI {
    KEYACTION getNextAction() throws IOException;

    void drawCharacter(Position position, char c, String color);

    void drawText(Position position, Text text);

    void drawTextImage(Position position, TextImage textImage);

    void drawRectangle(Position position, Size size, Appearance appearance);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    Size getSize();

    enum KEYACTION {
        UP,
        RIGHT,
        DOWN,
        LEFT,
        SLURP_UP,
        SLURP_RIGHT,
        SLURP_DOWN,
        SLURP_LEFT,
        SELECT,
        PAUSE,
        RESTART,
        SKIP,
        QUIT,
        CLOSE,
        MAIN,
        NONE
    }
}
