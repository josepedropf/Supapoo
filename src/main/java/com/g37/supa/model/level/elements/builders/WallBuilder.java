package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.Wall;

import java.io.IOException;

public class WallBuilder extends LevelElementBuilder {
    public WallBuilder(Size elementSize) {
        super(elementSize);
    }

    @Override
    public Wall createElement(Position position) throws IOException {
        return new Wall(position, getTextImage());
    }

    @Override
    protected TextImage getTextImage() throws IOException {
        return new LoaderTextImageBuilder("wall").createTextImage();
    }
}
