package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.LevelElement;

import java.io.IOException;

public abstract class LevelElementBuilder<T extends LevelElement> {
    protected Size elementSize;

    LevelElementBuilder(Size elementSize) {
        this.elementSize = elementSize;
    }

    public abstract T createElement(Position position) throws IOException;

    protected abstract TextImage getTextImage() throws IOException;
}
