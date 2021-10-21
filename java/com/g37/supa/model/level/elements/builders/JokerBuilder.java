package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.Joker;

import java.io.IOException;

public class JokerBuilder extends LevelElementBuilder {
    public JokerBuilder(Size elementSize) {
        super(elementSize);
    }

    @Override
    public Joker createElement(Position position) throws IOException {
        return new Joker(position, getTextImage());
    }

    @Override
    protected TextImage getTextImage() throws IOException {
        return new LoaderTextImageBuilder("joker").createTextImage();
    }
}
