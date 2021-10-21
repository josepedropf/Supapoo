package com.g37.supa.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextImageTest {
    private TextImage textImage;
    private Appearance appearance1, appearance2;

    @BeforeEach
    void setUp() {
        appearance1 = new Appearance(' ', "#000000","#000000");
        appearance2 = new Appearance('A', "#000000","#000000");
        textImage = new TextImage(new Size(3, 3), appearance1);
    }

    @Test
    void setAppearance() {
        textImage.setAppearanceAt(new Position(2, 2), appearance2);
        assertEquals(appearance1, textImage.getAppearanceAt(new Position(2, 1)));
        assertEquals(appearance2, textImage.getAppearanceAt(new Position(2, 2)));
    }
}
