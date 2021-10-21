package com.g37.supa.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextImageBuilderTest {
    private TextImage textImage;
    private LoaderTextImageBuilder textImageBuilder;

    @BeforeEach
    void setUp() throws IOException {
        textImageBuilder = new LoaderTextImageBuilder("test");
        textImage = textImageBuilder.createTextImage();
    }

    @Test
    void getSize() {
        assertEquals(9, textImage.getSize().getWidth());
        assertEquals(4, textImage.getSize().getHeight());
    }

    @Test
    void getAppearance() {
        assertEquals("#ff0000", textImage.getAppearanceAt(new Position(0, 0)).getBackgroundColor());
        assertEquals("#00ff00", textImage.getAppearanceAt(new Position(4, 2)).getBackgroundColor());
        assertEquals("#0000ff", textImage.getAppearanceAt(new Position(8, 3)).getBackgroundColor());
    }
}
